var vm = new Vue({
	el: '#app',
	components: {
      // all Keen UI components already registered
	},
	data: globalDataVue,
	computed:{
		
	},
	methods: {
		obtenerDatosIniciales: function(){
			mui.screen.showPage('mui-screen-page', 'SLIDE_UP');
		},
		showEdificio: function(){
			mui.viewport.showPage('otra-page','NONE');
		},
		hideLeftMenu: function(){
			if(vm.isLeftPanelCollapsed){
				vm.isLeftPanelCollapsed = false;
			} else {
				vm.isLeftPanelCollapsed = true;
			}
		},
		selectItemLeftPanel: function(option, item){
			if(option.id == 1){
				mui.viewport.showPage('list-buildings-page','SLIDE_UP');
			} else if(option.id == 2){
//				this.verTransacciones();
			}
		},
		selectItemUsuario: function(option, item){
			if(option.id == 1){
				this.mostrarReestablecerContrasena();
			} else if(option.id == 2){
				this.goLogaut();
			}
		},
		openModal(ref){
			this.$refs[ref].open();
		},
		closeModal(ref){
			this.$refs[ref].close();
		},
		goLogin: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: serviceURL + '/management/systemStartup',
				type: 'GET',
				headers: {
					'Accept':'application/json',
					'content-type':'application/x-www-form-urlencoded'
				},
				data: {
//					comando: 'login',
//					userName: vm.login.userName,
//					password: vm.login.password
				},
				success: function(data){
//					if(data.estado==0){
//						if(data.usuario.token.length > 0){
//							vm.usuario = data.usuario;
//							localStorage.setItem('token', data.usuario.token);
//							localStorage.setItem('usuario', data.usuario.usuario);
							iniciarSistema();
							vm.showLeftPanel = true;
							mui.screen.showPage('mui-screen-page' , 'NONE');
							mui.busy(false);
//						} else {
//							vm.login.invalidName = true;
//							vm.login.invalidPassword = true;
//							mui.busy(false);
//							me.mostrarError(data.mensaje);
//						}
//					} else {
//						vm.login.invalidName = true;
//						vm.login.invalidPassword = true;
//						mui.busy(false);
//						me.mostrarError(data.mensaje);
//					}
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
		},
		gotoBuilding: function(building){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: serviceURL + '/buildings/' + building.id,
				type: 'GET',
				headers: {
					'Accept':'application/json',
					'content-type':'application/x-www-form-urlencoded'
				},
				data: {},
				success: function(data){
					vm.building = data
					mui.busy(false);
					mui.viewport.showPage('building-page', 'SLIDE_UP');
				},
				error: function(err,estado, error){
					mui.busy(false);
//					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
		},
		gotoUnitsList: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: serviceURL + '/units?idBuilding' + vm.building.id,
				type: 'GET',
				headers: {
					'Accept':'application/json',
					'content-type':'application/x-www-form-urlencoded'
				},
				data: {},
				success: function(data){
					vm.units = data
					mui.busy(false);
					mui.viewport.showPage('units-list-page', 'SLIDE_UP');
				},
				error: function(err,estado, error){
					mui.busy(false);
//					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
//			vm.units = vm.building.units;
//			mui.viewport.showPage('units-list-page','SLIDE_UP');
		},
		loadUnitBoxSettlementMonth: function(){
			if(vm.transaction && vm.transaction && vm.transaction.monthLiquidation.value > 0 && vm.transaction.unit.value > 0){
				var me = this;
				mui.busy(true);
				mui.ajax({
					url: serviceURL + '/boxesSettlementMonths?settlementMonth=' + vm.transaction.monthLiquidation.value + '&unit=' + vm.transaction.unit.value,  
					type: 'GET',
					headers: {
						'Accept':'application/json',
						'content-type':'application/x-www-form-urlencoded'
					},
					data: {},
					success: function(data){
						vm.resultsUnitsBoxesMonthsLiquidation = data
						me.formatDataUnitsBoxesMonthsLiquidation();
						mui.busy(false);
					},
					error: function(err,estado, error){
						mui.busy(false);
	//					me.mostrarError('Error al intentar comunicarse con el servidor');
					}
				});
			}
		},
		saveTransaction: function(type) {
			if(vm.transaction.amount > 0){
				if(vm.transaction.monthLiquidation.value > 0 && vm.transaction.date != null 
					&& ((type === 'unit' && vm.transaction.unit && vm.transaction.unit.value > 0) 
					|| (type === 'supplier' && vm.transaction.supplier && vm.transaction.supplier.value > 0
					&& vm.transaction.box && vm.transaction.box.value > 0))){
						//ver que sucede si el monto que se paga es menor al que se debe pagar cuando es de unidad
						
						var me = this;
						var boxUnitTransactions;
						var endPoint = type === 'unit' ? 'units' : 'suppliers';
						mui.busy(true);
						var date = vm.transaction.date.toISOString().split('T')[0];

						if(type === 'unit') {
							var boxUnitTransactions = vm.transaction.unitsBoxes.map(boxUnit => ({
								box: { id: boxUnit.box },
								amount: boxUnit.amount
							}));
						}

						var data = {
							settlementMonth: { id: vm.transaction.monthLiquidation.value },
							totalAmount: vm.transaction.amount,
							description: vm.transaction.observation,
							unit: type === 'unit' ? { id: vm.transaction.unit.value } : null,
							transactionType: type,
							date: date,
							building: { id: vm.building.value },
							boxUnitTransactions: boxUnitTransactions,
							supplier: type === 'supplier' ? { id: vm.transaction.supplier.value } : null,
							box: type === 'supplier' ? { id: vm.transaction.box.value } : null,
							invoiceNumber: vm.transaction.invoiceNumber
						};
			
						mui.ajax({
							url: serviceURL + '/transactions/' + endPoint,
							type: 'POST',
							headers: {
								'Accept': 'application/json',
								'Content-Type': 'application/json'
							},
							data: JSON.stringify(data),
							success: function(data) {
								mui.busy(false);
								
							},
							error: function(err, estado, error) {
								mui.busy(false);
								
							}
						});
					}
			}
		},
		volverPagina: function(){
			mui.history.back();
		},
		formatDataUnitsBoxesMonthsLiquidation: function(){
			vm.unitsBoxesMonthsLiquidation = [];
			vm.transaction.unitsBoxes = [];
			vm.amountSettlementMonth = 0.0;
			if(vm.resultsUnitsBoxesMonthsLiquidation && vm.resultsUnitsBoxesMonthsLiquidation.length > 0){
				for (var i = 0; i < vm.resultsUnitsBoxesMonthsLiquidation.length; i++) {
					for (var x = 0; x < vm.resultsUnitsBoxesMonthsLiquidation[i].unitsboxesSettlementMonths.length; x++) {
						var currentItem = vm.resultsUnitsBoxesMonthsLiquidation[i].unitsboxesSettlementMonths[x]; 

						var newObject = {
							id: currentItem.id,
							name: currentItem.boxName,
							previousBalance: currentItem.previousBalance,
							currentBalance: currentItem.currentBalance,
							amountSettlementMonth: currentItem.amountSettlementMonth
						};
						//vm.transaction.unitsBoxes.push({ id: currentItem.id, amount: currentItem.amountSettlementMonth });
						vm.transaction.unitsBoxes.push({ id: currentItem.id, box: currentItem.idBox, amount: 0 });
						vm.amountSettlementMonth += currentItem.amountSettlementMonth;
						vm.unitsBoxesMonthsLiquidation.push(newObject);
					}
				}
			}
		},
		checkAmountTransaction: function() {
			if(vm.transaction.amount >= vm.amountSettlementMonth) {
				for(var x = 0; x < vm.unitsBoxesMonthsLiquidation.length; x++) {
					vm.transaction.unitsBoxes[x].amount = vm.unitsBoxesMonthsLiquidation[x].amountSettlementMonth;
				}
			}
		},
		showNewTransactionForm: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: serviceURL + '/settlementMonths?idBuilding' + vm.building.id,
				type: 'GET',
				headers: {
					'Accept':'application/json',
					'content-type':'application/x-www-form-urlencoded'
				},
				data: {},
				success: function(data){
					vm.monthsLiquidation = data
					mui.busy(false);
					mui.viewport.showPage('form-transaction-page', 'SLIDE_UP');
				},
				error: function(err,estado, error){
					mui.busy(false);
//					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
		},
		goLogaut: function(){

		},
		/*showMessage: function(message) {
			vm.messageModal.title = 'Administracion Juncal';
			vm.messageModal.message = message;
			this.openModal('message-modal');
		},*/
		onAccordionOpen(id) {
            Object.keys(this.accordions).forEach(key => {
                this.accordions[key] = key == id; // eslint-disable-line eqeqeq
            });
        },
        onAccordionClose(key) {
            this.accordions[key] = false;
        }
	},
	mounted: function(){
		this.$nextTick(function(){
			$('#app').show();
		})
	}
});

//All ready!. Page &  Cordova loaded.
//Todo listo!. Página & Cordova cargados.
function deviceReady() {
	
	try {
//		if(localStorage.getItem('token').length > 0){
//			this.iniciarSistema();
//		}
		
		//Sample when Internet connection is needed but not mandatory
		//Ejemplo de cuando se necesita conexióna a Internet pero no es obligatoria.
		if (!mui.connectionAvailable()){
			if ('plugins' in window && 'toast' in window.plugins)
				mui.toast('We recommend you connect your device to the Internet');
			else
				mui.alert('We recommend you connect your device to the Internet');
		}
		
		//Hide splash.
		//Ocultar el splash.
		if (navigator.splashscreen) {
			navigator.splashscreen.hide();
		}
		
		mui.viewport.on('showpage', function(prevPageId, pageId, effect, parameters, isBack){ 
			if(pageId =! 'login-page'){
				vm.showLeftPanel = true;
			} 
//			if(pageId =! 'historial-montos-page'){
//				vm.detalleMontoFijo.invalidGC = false;
//				vm.detalleMontoFijo.invalidFR = false;
//				vm.detalleMontoFijo.invalidFO = false;
//				vm.detalleMontoFijo.invalidFC = false;
//				vm.detalleMontoFijo.invalidCH = false;
//			} 
//			if(pageId =! 'home-page'){
//				vm.habilitarVolver = true;
//			}
		});

		//Install events, clicks, resize, online/offline, etc. 
		installEvents();

	} catch (e) {
		mui.alert(e.message);
	}
}

/**
 * Install events, clicks, resize, online/offline, etc., on differents HTML elements.
 * Instala eventos, clicks, resize, online/offline, etc., sobre diferentes elementos HTML.
 */
function installEvents() {
//	vm.menuOptions = [
//	    {
//	        id: 1,
//	        label: 'Edificios',
//	        icon: 'business',
//	    },
//	    {
//	        id: 2,
//	        label: 'Pagos',
//	        icon: 'attach_money',
//	    },
//	    {
//	        id: 3,
//	        label: 'Usuarios',
//	        icon: 'group',
//	    },
//	    {
//	        id: 4,
//	        label: 'Configuracion',
//	        icon: 'settings',
//	    },
//	    {
//	        id: 5,
//	        label: 'Archivos',
//	        icon: 'cloud_queue',
//	    },
//	    {
//	        id: 6,
//	        label: 'Mensajes',
//	        icon: 'email',
//	    },
//	    {
//	        id: 7,
//	        label: 'Contacto',
//	        icon: 'sms',
//	    }
//	];
	
	mui.util.installEvents([
		//It's a good idea to consider what happens when the device is switched on and off the internet.
		//Es buena idea considerar que pasa cuando el dispositivo se conecta y desconecta a Internet.
		{
			id: document,
			ev: 'online',
			fn: () => {
				mui.alert('online','Connection');
			}
		},
		{
			id: document,
			ev: 'offline',
			fn: () => {
				mui.alert('offline','Connection');
			}
		},
		//Typically fired when the device changes orientation.
		//Típicamente disparado cuando el dispositivo cambia de orientación.
		{
			id: window,
			ev: 'resize',
			fn: () => {
				console.log('resize');
			}
		},
		//Mail list click/touch events. See that if the event is not specified, click is assumed.
		{
			id: '.mui-backarrow',
			fn: () => {
				mui.history.back();
				return false;
			}
		},
		{
			id: '.mui-headmenu, #gomodal',
			fn: () => {
				mui.screen.showPanel('menu-panel', 'SLIDE_LEFT');	//ATENTION!!! mui.screen instead mui.viewport
				return false;
			}
		},
		{
			id: '#gocontent',
			fn: () => {
				mui.viewport.showPage('content-page','DEF');
				return false;
			}
		},
		{
			id: '#golist',
			fn: () => {
				mui.viewport.showPage('list-page','DEF');
				return false;
			}
		},
		{
			id: '#golisticon',
			fn: () => {
				return false;
			}
		},
		{
			id: '#gotrans',
			fn: () => {
				mui.viewport.showPage('transitions-page','DEF');
				return false;
			}
		},
		{
			id: '#gotest',
			fn: () => {
				mui.viewport.showPage('api-page','DEF');
				return false;
			}
		},
		//Toolbar options ------------------------------------------
		{
			id: '#tabbar-button1',
			fn: () => {
				mui.viewport.showPage('home-page', 'NONE');
				mui.history.reset();	//Look at this!
				return false;
			}
		},
		{
			id: '#tabbar-button2',
			fn: () => {
				mui.history.reset();	//Look at this!
				openInAppBrowser('http://www.mobileui.org');
				return false;
			}
		},
		{
			id: '#tabbar-button3',
			fn: () => {
				mui.history.reset();	//Look at this!
				mui.viewport.showPage('content-page', 'DEF');
				return false;
			}
		},
		{
			id: '#tabbar-button4',
			fn: () => {
				openInAppBrowser('http://www.facebook.com');
				mui.history.reset();	//Look at this!
				return false;
			}
		},
		{
			id: '#tabbar-button5',
			fn: () => {
				mui.alert('MobileUI version ' + mui.version.toString());
				mui.history.reset();	//Look at this!
				return false;
			}
		},
		{
			id: '#samplelist',
			fn: () => {
				return false;
			}
		},
		//API test options
		{
			id: '#api-cordova',
			fn: () => {
				if (mui.cordovaAvailable())
					mui.alert('Cordova/Phonegap is available!', 'Hurrah');
				else
					mui.alert('Cordova/Phonegap not available.');
				return false;
			}
		},
		{
			id: '#api-ismobile',
			fn: () => {
				if (mui.isMobileDevice.any())
					mui.alert('True', 'Hurrah');
				else
					mui.alert('False');
				return false;
			}
		},
		{
			id: '#api-vibrate',
			fn: () => {
				if (mui.cordovaAvailable())
					mui.vibrate();
				else
					mui.alert('Vibrate unavailable');
				return false;
			}
		},
		{
			id: '#api-busy',
			fn: () => {
				mui.busy(true);
				setTimeout(function() {
					mui.busy(false);
				}, 2000);
			}
		},
		{
			id: '#api-alert',
			fn: () => {
				mui.alert('Hello MUI', 'Cheers');
				return false;
			}
		},
		{
			id: '#api-confirm',
			fn: () => {
				mui.confirm('Are you happy?', function(buttonIndex) {
					mui.alert('Yo press button ' + buttonIndex, 'Result');
				},
				'Happiness',
				['Yes', 'No']
			);
			return false;
			}
		},
		{
			id: '#api-prompt',
			fn: () => {
				mui.prompt('How old are you?', function(result) {
					mui.alert('You pressed button ' + result.buttonIndex + '. Value=' + result.input1, 'Result');
				},
				'Age',
				['Ok', 'No thanks!'],
				'90'
			);
			return false;
			}
		},
		{
			id: '#api-connection',
			fn: () => {
				mui.alert(mui.getConnectionType(), 'Connection Type');
				return false;
			}
		},
		{
			id: '#api-toast',
			fn: () => {
				var msg;
				if (mui.cordovaAvailable() && mui.isMobileDevice.any())
					msg = 'This is a toast message';
				else
					msg = 'Using mui.alert() for compatibility when toast plugin are not available';
				mui.toast(msg, 'center', 'long');
				return false;
			}
		},
		{
			id: '#api-version',
			fn: () => {
				mui.alert(mui.version.toString(), 'Version');
				return false;
			}
		},
		{
			id: '#api-platform',
			fn: (currentPageId, originalTarget, event, startX, startY, endX, endY) => {
				if (mui.cordovaAvailable() && device && device.platform) {
					alert(device.model + '; ' + device.platform + ' - ' + device.version);
				}
				return false;
			}
		},
		//MobileUI viewport specific event.
		{
			vp: mui.viewport,
			ev: 'swipeleftdiscover',
			fn: () => {
				if (!mui.viewport.panelIsOpen()) {
					mui.history.back();
				}
			}
		}
	]);
	
	//Old fashion events style. Yes, of course is possible. It's a web standard!
	//jQuery
	$('#samplelist').click(function() {
		mui.alert('Nothing to do!', "Attention");
		return false;
	});
	
	//Pure javascript
	var menuOptions = document.getElementById('menuoptions')
	menuOptions.addEventListener('click', function() {
//		mui.alert('Sorry. Nothing to do!', "Attention");
		return false;
	}, false);
	
}


/**
 * Courtesy: Open an url using InAppBrowser plugin.
 * Cortesía: Abre una url usando InAppBrowser plugin.
 * @param url
 */
function openInAppBrowser(url) { 
	window.open(encodeURI(url), '_blank', 'location=yes,closebuttoncaption=Volver,presentationstyle=pagesheet,transitionstyle="fliphorizontal",EnableViewPortScale=yes');
}

/**
 * Util function to force page link to be open in InAppBrowser.
 * Función Util para forzar que los links se abran en InAppBrowser.
 * @param id
 */
function linksForInAppBrowser(pageId){
	var idd = '#'+pageId ;
	$(idd).find('a').each(function (index, element) {
		var href = $(this).attr('href');
		$(this).attr('href', '#');
		$(this).attr('target', '_self');
		$(this).attr('onclick', 'window.open("' + href + '", "_blank");');
	});
}


function iniciarSistema(){
	mui.busy(true);
	mui.ajax({
		url: 'http://localhost:8090/management/systemStartup',
		type: 'GET',
		headers: {
			'Accept':'application/json',
			'content-type':'application/x-www-form-urlencoded'
		},
		data: {
//			comando: 'iniciosistema',
//			usuario: localStorage.getItem('usuario'),
//			token: localStorage.getItem('token')
		},
		success: function(data, status, xhr){
			if(xhr.status==200){
				vm.menuOptions = data.itemsMenu;
          		vm.buildings = data.buildings;
          		vm.suppliers = data.suppliers;
				vm.showLeftPanel = true;
				mui.screen.showPage('mui-screen-page' , 'NONE');
				mui.busy(false);
			} else {
				mui.busy(false);
//				me.mostrarError(data.mensaje);
			}
		},
		error: function(err,estado, error){
			mui.busy(false);
//			me.mostrarError('Error al intentar comunicarse con el servidor');
		}
	});
}

