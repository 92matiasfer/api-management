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
				mui.viewport.showPage('listado-edificios-page','SLIDE_UP');
			} else if(option.id == 2){
				this.verTransacciones();
			} else if(option.id == 3){
				this.obtenerUsuarios();
			} else if(option.id == 4){
				mui.viewport.showPage('configuracion-page','SLIDE_UP');
			} else if(option.id == 5){
				if(vm.usuario.tipo=='A'){
					mui.viewport.showPage('archivos-page','SLIDE_UP');
				} else if(vm.usuario.tipo=='C'){
					 this.verMisArchivos();
				}
			} else if(option.id == 6) {
				this.obtenerMensajes('recibidos');
			} else if(option.id == 7) {
				mui.viewport.showPage('contacto-page','SLIDE_UP')
			} else if(option.id == 8) {
				this.verEstadoCuenta();
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
				url: servicioURL,
				type: 'POST',
				headers: {
					'Accept':'aplication/json',
					'content-type':'application/x-www-form-urlencoded'
				},
				data: {
					comando: 'login',
					userName: vm.login.userName,
					password: vm.login.password
				},
				success: function(data){
					if(data.estado==0){
						if(data.usuario.token.length > 0){
							vm.usuario = data.usuario;
							localStorage.setItem('token', data.usuario.token);
							localStorage.setItem('usuario', data.usuario.usuario);
							iniciarSistema();
							vm.showLeftPanel = true;
							mui.screen.showPage('mui-screen-page' , 'NONE');
//							mui.viewport.showPage('listado-edificios-page','NONE');
							mui.busy(false);
						} else {
							vm.login.invalidName = true;
							vm.login.invalidPassword = true;
							mui.busy(false);
							me.mostrarError(data.mensaje);
						}
					} else {
						vm.login.invalidName = true;
						vm.login.invalidPassword = true;
						mui.busy(false);
						me.mostrarError(data.mensaje);
					}
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
		},
		volverPagina: function(){
			mui.history.back();
		},
		goLogaut: function(){
			vm.showLeftPanel = false;
			localStorage.setItem('token','');
			localStorage.setItem('usuario', '');
			$('#login-page').show();
			mui.screen.showPage('login-page', 'NONE');
		},
		verEdificio: function(edificio){
			mui.busy(true);
//			var fecha = new Date();
//			var fechaActual = fecha.getFullYear() + '-' + (fecha.getFullMonth() + 1) + '-' + fecha.getDate(); 
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obteneredificio',
					token: localStorage.getItem('token'),
					usuario: localStorage.getItem('usuario'),
					idEdificio: edificio.id
				},
				success: function(data){
					if(data.estado == 0){
						vm.edificio = data.edificio;
						vm.mesesLiquidacionEdificio = data.edificio.liquidaciones;
//						vm.fechaActual = fechaActual;
						mui.busy(false);
						mui.viewport.showPage('edificio-page', 'SLIDE_UP');
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
		},
		mostrarReestablecerContrasena: function(){
			mui.viewport.showPage('form-reestablecer-Contrasena', 'SLIDE_UP');
		},
		recuperarContrasena: function(){
			this.openModal('recuperar-con');
		},
		reestablecercontrasena: function(){
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'reestablecercontrasena',
					usuario: vm.usuarioRes.usuario,
				},
				success: function(data){
					if(data.status == 0){
						alert('Se ha enviado una nueva contraseña a su email');
					} else {
						alert(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,status, error){
					mui.busy(false);
					alert('Error al intentar comunicarse con el servidor');
				}
			});
		},
		verTransacciones: function(){
			var me = this;
			var fechaInicio = '';
			var fechaFin = '';
			var idUnidad = 0;
			var tipoTransaccion = 0;
			var enRed = 0;
			if(vm.filtrosPagos.enRedCobranza){
				enRed = 1;
			}
			if(vm.usuario.rol=='Copropietario'){
				idUnidad = vm.usuario.idUnidad
			} else {
				idUnidad = vm.filtrosPagos.unidad.value;
				tipoTransaccion = vm.filtrosPagos.tipoTransaccion.value;
			}
			if(vm.filtrosPagos.fechaInicio != null){
				var mes = '';
				var dia = '';
				if((vm.filtrosPagos.fechaInicio.getMonth() + 1) < 10) {
					mes = '0' + (vm.filtrosPagos.fechaInicio.getMonth() + 1);
				} else {
					mes = vm.filtrosPagos.fechaInicio.getMonth() + 1;
				}
				if(vm.filtrosPagos.fechaInicio.getDate() < 10){
					dia = '0' + vm.filtrosPagos.fechaInicio.getDate();
				} else {
					dia = vm.filtrosPagos.fechaInicio.getDate();
				}
				fechaInicio = vm.filtrosPagos.fechaInicio.getFullYear() + '-' + mes + '-' + dia;
			}
			if(vm.filtrosPagos.fechaFin != null){
				var mes = '';
				var dia = '';
				if((vm.filtrosPagos.fechaFin.getMonth() + 1) < 10) {
					mes = '0' + (vm.filtrosPagos.fechaFin.getMonth() + 1);
				} else {
					mes = vm.filtrosPagos.fechaFin.getMonth() + 1;
				}
				if(vm.filtrosPagos.fechaFin.getDate() < 10){
					dia = '0' + vm.filtrosPagos.fechaFin.getDate();
				} else {
					dia = vm.filtrosPagos.fechaFin.getDate();
				}
				fechaFin = vm.filtrosPagos.fechaFin.getFullYear() + '-' + mes + '-' + dia;
			}
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenertransacciones',
					token: vm.usuario.token,
					idEdificio: vm.filtrosPagos.edificio.value,
					proveedor: vm.filtrosPagos.proveedor.value,
					tipoTransaccion: tipoTransaccion,
					unidad: idUnidad,
					fechaInicio: fechaInicio,
					fechaFin: fechaFin,
					enRed: enRed
				},
				success: function(data){
					if(data.estado == 0){
						if(data.transacciones.length > 0){
							vm.existenTransacciones = true;
							vm.transacciones = data.transacciones;
							if(tipoTransaccion == 1){
								vm.tablaTransacciones.unidad = true;
								vm.tablaTransacciones.proveedor = false;
							} else if(tipoTransaccion == 2){
								vm.tablaTransacciones.proveedor = true;
								vm.tablaTransacciones.unidad = false;
							}
						} else {
							vm.existenTransacciones = false;
						}
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.viewport.showPage('pagos-page','SLIDE_UP');
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}
			});
		},
		limpiarFiltrosPagos: function(){
			vm.filtrosPagos.edificio = {
				id : 0,
				nombre : '',
				direccion : '',
				unidades : []  
			};
			vm.filtrosPagos.proveedor = {
				id : 0,
				nombre : '',
				descripcion : '',
				rut : ''
			};
			vm.filtrosPagos.tipoTransacciones = {
					label : 'Copropietario',
					value : 1
			},
			vm.filtrosPagos.fechaInicio = null,
			vm.filtrosPagos.fechaFin = null,
			vm.filtrosPagos.unidad = {
				value: 0
			};
			vm.filtrosPagos.enRedCobranza = false;
		},
		mostrarFormIngresoPago: function(){
			mui.viewport.showPage('form-pagos-page', 'SLIDE_UP');
		},
		mostrarFormCerrarLiquidacion: function(){
			this.openModal('formulario-cerrar-liquidacion');	
		},
		mostrarFormLiquidacion: function(){
			this.openModal('formulario-generar-liquidacion');	
		},
		verFormularioNuevoProveedor: function(){
			this.openModal('formulario-nuevo-proveedor');
		},
		guardarNuevoProveedor: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'guardarnuevoproveedor',
					token: vm.usuario.token,
					nombre: vm.nuevoProveedor.nombre,
					rut: vm.nuevoProveedor.rut,
					descripcion: vm.nuevoProveedor.descripcion
				},
				success: function(data){
					if(data.estado == 0){
						vm.proveedores = data.proveedores;
						me.closeModal('formulario-nuevo-proveedor');
						me.mostrarMsj(data.mensaje, false, false, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError(data.mensaje);
				}
			});
		},
		cargarMontos: function(){
			var me = this;
			if(vm.nuevaTransaccion.unidad.value > 0 && vm.nuevaTransaccion.mesLiquidacion.value.length > 0) {
				var montoIngresado = vm.nuevaTransaccion.monto;
				var totalaPagar = 0;
				var saldoActual = 0;
				mui.busy(true);
				mui.ajax({
					url: servicioURL,
					type: 'POST',
					data: {
						comando: 'obtenermontosapagar',
						token: vm.usuario.token,
						unidad: vm.nuevaTransaccion.unidad.value,
						edificio: vm.edificio.id,
						mesLiquidacion: vm.nuevaTransaccion.mesLiquidacion.value
					},
					success: function(data){
						if(data.estado == 0){
							vm.nuevaTransaccion.debePagarGC = data.uml.debePagarGC;
							vm.nuevaTransaccion.debePagarFR = data.uml.debePagarFR;
							vm.nuevaTransaccion.debePagarFC = data.uml.debePagarFC;
							vm.nuevaTransaccion.debePagarFO = data.uml.debePagarFO;
							vm.nuevaTransaccion.debePagarCH = data.uml.debePagarCH;
							vm.nuevaTransaccion.saldoActual = data.uml.saldoActual;
							vm.nuevaTransaccion.saldoAnterior = data.uml.saldoAnterior;
							if(vm.nuevaTransaccion.saldoActual > 0){
								vm.nuevaTransaccion.monto = vm.nuevaTransaccion.saldoActual;
								vm.nuevaTransaccion.fondoReserva = vm.nuevaTransaccion.debePagarFR;
								vm.nuevaTransaccion.gastosComunes = vm.nuevaTransaccion.debePagarGC;
								vm.nuevaTransaccion.fondoObras = vm.nuevaTransaccion.debePagarFO;
								vm.nuevaTransaccion.fondoCalefaccion = vm.nuevaTransaccion.debePagarFC;
								vm.nuevaTransaccion.fondoCochera = vm.nuevaTransaccion.debePagarCH;
							} else {
								vm.nuevaTransaccion.monto = 0;
							}
						} else if(data.estado == 400){
							mui.busy(false);
							me.goLogaut();
						} else {
							me.mostrarError(data.mensaje); 
						}
						mui.busy(false);
					},
					error: function(err,estado, error){
						mui.busy(false);
						me.mostrarError(data.mensaje); 
					}
				});
			}
		},
		chequearMontoIngresado: function(){
			var totalaPagar = 0;
			var restoSaldo = 0;
			var montoIngresado = 0;
			var pagoDisp = 0;
			montoIngresado = Number.parseInt(vm.nuevaTransaccion.monto, 10);
			vm.nuevaTransaccion.invalidMonto = false;
			totalaPagar = vm.nuevaTransaccion.saldoActual;
			if(montoIngresado >= totalaPagar) {
				vm.nuevaTransaccion.fondoReserva = vm.nuevaTransaccion.debePagarFR;
				vm.nuevaTransaccion.gastosComunes = vm.nuevaTransaccion.debePagarGC;
				vm.nuevaTransaccion.fondoObras = vm.nuevaTransaccion.debePagarFO;
				vm.nuevaTransaccion.fondoCalefaccion = vm.nuevaTransaccion.debePagarFC;
				vm.nuevaTransaccion.fondoCochera = vm.nuevaTransaccion.debePagarCH;
			} else {
				vm.nuevaTransaccion.fondoReserva = 0;
				vm.nuevaTransaccion.gastosComunes = 0;
				vm.nuevaTransaccion.fondoObras = 0;
				vm.nuevaTransaccion.fondoCalefaccion = 0;
				vm.nuevaTransaccion.fondoCochera = 0;	
				pagoDisp = montoIngresado;
				restoSaldo = totalaPagar;
				if (montoIngresado >= vm.nuevaTransaccion.saldoAnterior){
					pagoDisp -= vm.nuevaTransaccion.saldoAnterior;					
					if (pagoDisp >= vm.nuevaTransaccion.debePagarGC){
						pagoDisp -= vm.nuevaTransaccion.debePagarGC;						
						vm.nuevaTransaccion.gastosComunes = vm.nuevaTransaccion.debePagarGC;
						if (pagoDisp >= vm.nuevaTransaccion.debePagarFR){
							pagoDisp -= vm.nuevaTransaccion.debePagarFR;							
							vm.nuevaTransaccion.fondoReserva = vm.nuevaTransaccion.debePagarFR;
							if (pagoDisp >= vm.nuevaTransaccion.debePagarFO){
								pagoDisp -= vm.nuevaTransaccion.debePagarFO;								
								vm.nuevaTransaccion.fondoObras = vm.nuevaTransaccion.debePagarFO;
								if (pagoDisp >= vm.nuevaTransaccion.debePagarFC){
									pagoDisp -= vm.nuevaTransaccion.debePagarFC;									
									vm.nuevaTransaccion.fondoCalefaccion = vm.nuevaTransaccion.debePagarFC;
								} else {												// Si fondo calefacción es el último fondo
									vm.nuevaTransaccion.fondoCalefaccion = pagoDisp;											
								}
							} else {
								vm.nuevaTransaccion.fondoObras = pagoDisp;
							}
						} else {
							vm.nuevaTransaccion.fondoReserva = pagoDisp;
						}
					} else {
						vm.nuevaTransaccion.gastosComunes = pagoDisp;
					}
				} 		
			}
			restoSaldo = totalaPagar - montoIngresado;
			vm.nuevaTransaccion.invalidMonto = true;
			vm.nuevaTransaccion.msjMonto = 'Nuevo saldo unidad: $' + restoSaldo; 
			
//			var totalaPagar = 0;
//			var restoSaldo = 0;
//			var montoIngresado = 0;
//			var pagoDisp = 0;
//			montoIngresado = Number.parseInt(vm.nuevaTransaccion.monto, 10);
//			vm.nuevaTransaccion.invalidMonto = false;
//			totalaPagar = vm.nuevaTransaccion.saldoActual;
//			if(montoIngresado >= totalaPagar) {
//				vm.nuevaTransaccion.fondoReserva = vm.nuevaTransaccion.debePagarFR;
//				vm.nuevaTransaccion.gastosComunes = vm.nuevaTransaccion.debePagarGC;
//				vm.nuevaTransaccion.fondoObras = vm.nuevaTransaccion.debePagarFO;
//				vm.nuevaTransaccion.fondoCalefaccion = vm.nuevaTransaccion.debePagarFC;
//				vm.nuevaTransaccion.fondoCochera = vm.nuevaTransaccion.debePagarCH;
//				restoSaldo = totalaPagar - montoIngresado;
//			} else {
//				vm.nuevaTransaccion.fondoReserva = 0;
//				vm.nuevaTransaccion.gastosComunes = 0;
//				vm.nuevaTransaccion.fondoObras = 0;
//				vm.nuevaTransaccion.fondoCalefaccion = 0;
//				vm.nuevaTransaccion.fondoCochera = 0;	
//				pagoDisp = montoIngresado;
//				restoSaldo = totalaPagar;
//				if (montoIngresado >= vm.nuevaTransaccion.saldoAnterior){
//					pagoDisp -= vm.nuevaTransaccion.saldoAnterior;
//					restoSaldo -= vm.nuevaTransaccion.saldoAnterior;
//					if (pagoDisp >= vm.nuevaTransaccion.debePagarGC){
//						pagoDisp -= vm.nuevaTransaccion.debePagarGC;
//						restoSaldo -= vm.nuevaTransaccion.debePagarGC;
//						vm.nuevaTransaccion.gastosComunes = vm.nuevaTransaccion.debePagarGC;
//						if (pagoDisp >= vm.nuevaTransaccion.debePagarFR){
//							pagoDisp -= vm.nuevaTransaccion.debePagarFR;
//							restoSaldo -= vm.nuevaTransaccion.debePagarFR;
//							vm.nuevaTransaccion.fondoReserva = vm.nuevaTransaccion.debePagarFR;
//							if (pagoDisp >= vm.nuevaTransaccion.debePagarFO){
//								pagoDisp -= vm.nuevaTransaccion.debePagarFO;
//								restoSaldo -= vm.nuevaTransaccion.debePagarFO;
//								vm.nuevaTransaccion.fondoObras = vm.nuevaTransaccion.debePagarFO;
//								if (pagoDisp >= vm.nuevaTransaccion.debePagarFC){
//									pagoDisp -= vm.nuevaTransaccion.debePagarFC;
//									restoSaldo -= vm.nuevaTransaccion.debePagarFC;
//									vm.nuevaTransaccion.fondoCalefaccion = vm.nuevaTransaccion.debePagarFC;
//								}
//							}
//						}
//					}
//
//				}			
//			}
//			vm.nuevaTransaccion.invalidMonto = true;
//			vm.nuevaTransaccion.msjMonto = 'Nuevo saldo unidad: $' + restoSaldo; 
		},
		cargarUnidadesSelect: function(comando){
			var edificio = 0;
			if(comando == 'nuevo-usuario') {
				edificio = vm.nuevoUsuario.edificio.value;
			} else if(comando == 'filtros-pagos'){
				edificio = vm.filtrosPagos.edificio.value;
			} else if(comando == 'mensajes'){
				edificio = vm.selMensaje.edificio.value;
			} else if(comando == 'nuevoMsj'){
				edificio = vm.contacto.edificio.value;
			}
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenerunidadesedificio',
					token: vm.usuario.token,
					idEdificio: edificio
				},
				success: function(data){
					if(data.estado == 0){
						vm.unidadesLista = data.unidades;
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						mui.busy(false);
						me.mostrarError(data.mensaje); 
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError(data.mensaje); 
				}
			});
		},
		verModalConfirmacion: function(comando){
			if(comando == 'eliminarUsuario'){
				this.closeModal('detalle-usuario')
				vm.confirmacionModal.comando = comando;
				vm.confirmacionModal.mensaje = '¿Esta seguro que desea eliminar al usuario ' + vm.detalleUsuario.usuario + ' del sistema?';
				this.openModal('confirmar-modal');
			}
		},
		eliminarUsuario: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'eliminarusuario',
					token: vm.usuario.token,
					usuario: vm.detalleUsuario.usuario
				},
				success: function(data){
					if(data.estado == 0){
						me.closeModal('detalle-usuario');
						me.closeModal('confirmar-modal');
						me.mostrarMsj(data.mensaje, false, false, false);
						me.obtenerUsuarios();
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError(data.mensaje);
				}				
			})
		},
		editarUsuario: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'editarusuario',
					idUsuario: vm.detalleUsuario.id,
					usuario: vm.detalleUsuario.usuario,
					nombre: vm.detalleUsuario.nombre,
					apellido: vm.detalleUsuario.apellido,
					telefono: vm.detalleUsuario.telefono,
					mail: vm.detalleUsuario.email,
					observacioens: vm.detalleUsuario.observaciones
				},
				success: function(data){
					if(data.estado == 0){
						me.closeModal('detalle-usuario');
						me.mostrarMensaje(data.mensaje, true, false);
						me.obtenerUsuarios();
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError(data.mensaje);
				}				
			})
		},
		habilitarEdicionUsuario: function(){
			if(vm.editorUsuario){
				vm.editorUsuario = false;
			} else {
				vm.editorUsuario = true;
			}
		},
		verTiposPagos: function(){
			if(vm.edificio.pagaCalefaccion == 1){
				vm.tipodePago.pagaFc = true;
			} else {
				vm.tipodePago.pagaFc = false;
			}
			if(vm.edificio.pagaCochera == 1){
				vm.tipodePago.pagaCh = true;
			} else {
				vm.tipodePago.pagaCh = false;
			}
			if(vm.edificio.pagaObras == 1){
				vm.tipodePago.pagaFo = true;
			} else {
				vm.tipodePago.pagaFo = false;
			}
			this.openModal('detalle-tipos-pagos');
		},
		guardarTiposPago: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'guardartipopagos',
					pagaFC: vm.tipodePago.pagaFc,
					pagaFO: vm.tipodePago.pagaFo,
					pagaCH: vm.tipodePago.pagaCh
				},
				success: function(data){
					if(data.estado == 0){
						me.mostrarMensaje(data.mensaje, true, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		cerrarLiquidacion: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'cerrarliquidacion',
					edificio: vm.edificio.id,
					mesLiquidacion: vm.mesCerrado.mesLiquidacion.value
				},
				success: function(data){
					if(data.estado == 0){
						me.closeModal('formulario-cerrar-liquidacion');
						me.mostrarMensaje(data.mensaje, true, false);
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		generarLiquidacion: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'generarliquidacion',
					token: vm.usuario.token,
					edificio: vm.edificio.id,
					mesLiquidacion: vm.nuevaLiquidacion.mesLiquidacion.value
				},
				success: function(data){
					if(data.estado == 0){
						me.closeModal('formulario-generar-liquidacion');
						me.mostrarMensaje(data.mensaje, true, false);
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		mostrarMsj: function(mensaje, accept, confirm, cancelar){
			vm.mensajeModal.title = 'Administracion Juncal';
			vm.mensajeModal.mensaje = mensaje;
			vm.mensajeModal.accept = accept;
			vm.mensajeModal.cancelar= cancelar;
			vm.mensajeModal.confirm = confirm;
			this.openModal('mensaje-modal');
		},
		mostrarMensaje: function(mensaje, accept, confirm){
			vm.mensajeModal.title = 'Administracion Juncal';
			vm.mensajeModal.mensaje = mensaje;
			vm.mensajeModal.accept = accept;
			vm.mensajeModal.confirm = confirm;
			vm.mensajeModal.cerrar = false;
			this.openModal('mesaje-modal');
		},
		mostrarError: function(mensaje){
			vm.mensajeModal.title = 'Advertencia';
			vm.mensajeModal.mensaje = mensaje;
			vm.mensajeModal.cancelar = false;
			vm.mensajeModal.accept = false;
			vm.mensajeModal.confirm = false;
			vm.mensajeModal.cerrar = true;
			this.openModal('mesaje-modal');
		},
		editarPagosTransaccion: function(){
			vm.nuevaTransaccion.dejareditarpagos = true;
		},
		controlarFechaPago: function(){
			if(vm.nuevaTransaccion.fecha != null){
				if(vm.nuevaTransaccion.fecha.getDate() > vm.edificio.fechaPago){
					vm.nuevaTransaccion.habilitarRecargo = true;
				}
			}
		},
		sumarRecargo: function(){
			if(vm.cantClickRec == 0){
				vm.nuevaTransaccion.saldoActualAux = vm.nuevaTransaccion.saldoActual;
				vm.nuevaTransaccion.montoAux = vm.nuevaTransaccion.monto;
			}
			vm.cantClickRec = vm.cantClickRec + 1;
			if(vm.nuevaTransaccion.cobrarRecargo){
				if(vm.nuevaTransaccion.saldoActual > 0){
					vm.nuevaTransaccion.saldoActual = Math.round(vm.nuevaTransaccion.saldoActual * (1 + (vm.edificio.recargo / 100)));
					vm.nuevaTransaccion.monto = Math.round(vm.nuevaTransaccion.monto * (1 + (vm.edificio.recargo / 100)));
				}
			} else {
				vm.nuevaTransaccion.saldoActual = vm.nuevaTransaccion.saldoActualAux;
				vm.nuevaTransaccion.monto = vm.nuevaTransaccion.montoAux;
			}
		},
		guardarTransaccion: function(tipoTransaccion){
			if(vm.nuevaTransaccion.monto > 0){
				if(vm.nuevaTransaccion.monto > 0 && vm.nuevaTransaccion.mesLiquidacion.value.length > 0 && vm.nuevaTransaccion.fecha != null && ((tipoTransaccion==1 && vm.nuevaTransaccion.unidad.value > 0) || (tipoTransaccion==2 && vm.nuevaTransaccion.proveedor.value > 0 && vm.nuevaTransaccion.tipoCaja.value > 0))){
					var montoFavor = 0;
					var montoGC = Number.parseInt(vm.nuevaTransaccion.gastosComunes, 10);
					var montoFR = Number.parseInt(vm.nuevaTransaccion.fondoReserva, 10);
					var montoFO = Number.parseInt(vm.nuevaTransaccion.fondoObras, 10);
					var montoFC = Number.parseInt(vm.nuevaTransaccion.fondoCalefaccion, 10);
					var montoCH = Number.parseInt(vm.nuevaTransaccion.fondoCochera, 10); 
					var saldoAnterior = Number.parseInt(vm.nuevaTransaccion.saldoAnterior, 10); 
					if(saldoAnterior < 0){
						montoFavor = saldoAnterior; 
					} 
					if((tipoTransaccion==1 && vm.nuevaTransaccion.monto >= (montoGC + montoFR + montoFO + montoFC + montoCH + montoFavor)) || tipoTransaccion==2){
						var recargo = 0;
						if(vm.nuevaTransaccion.cobrarRecargo){
							recargo = 1;
						}
						var me = this;
						mui.busy(true);
						var fecha = '';
						fecha = vm.nuevaTransaccion.fecha.getFullYear() + '-' + (vm.nuevaTransaccion.fecha.getMonth() + 1) + '-' + vm.nuevaTransaccion.fecha.getDate();
						mui.ajax({
							url: servicioURL,
							type: 'POST',
							data: {
								comando: 'nuevatransaccion',
								token: vm.usuario.token,
								monto: vm.nuevaTransaccion.monto,
								gastosComunes: vm.nuevaTransaccion.gastosComunes,
								fondoReserva: vm.nuevaTransaccion.fondoReserva,
								fondoObra: vm.nuevaTransaccion.fondoObras,
								fondoCalefaccion: vm.nuevaTransaccion.fondoCalefaccion,
								fondoCocheras: vm.nuevaTransaccion.fondoCochera,
								edificio: vm.edificio.id,
								proveedor: vm.nuevaTransaccion.proveedor.value,
								unidad: vm.nuevaTransaccion.unidad.value,
								mesLiquidacion: vm.nuevaTransaccion.mesLiquidacion.value,
								observacion: vm.nuevaTransaccion.observacion,
								tipoTransaccion: tipoTransaccion,
								tipoCaja: vm.nuevaTransaccion.tipoCaja.value,
								nroFactura: vm.nuevaTransaccion.nroFactura,
								fecha: fecha,
								recargo: recargo
							},
							success: function(data){
								if(data.estado == 0){
									vm.nuevaTransaccion.invalidFecha = false;
									vm.nuevaTransaccion.invalidMonto = false;
									vm.nuevaTransaccion.invalidMesLiquidacion = false;
									vm.nuevaTransaccion.invalidProveedor = false;
									vm.nuevaTransaccion.invalidCaja = false;
									me.restablecerFormPagos();
									me.mostrarMsj(data.mensaje, false, false, false);
								} else if(data.estado == 400){
									mui.busy(false);
									me.goLogaut();
								} else {
									me.mostrarError(data.mensaje);
								}
								mui.busy(false);
							},
							error: function(err,estado, error){
								mui.busy(false);
								me.mostrarError('Error al intentar comunicarse con el servidor');
							}				
						})
					} else {
						this.mostrarError('El monto ingresado no puede ser menor al total de importes a pagar');
					}
				} else {
					this.mostrarError('Debe ingresar todos los datos solicitados');
					vm.nuevaTransaccion.invalidFecha = true;
					vm.nuevaTransaccion.invalidMonto = true;
					vm.nuevaTransaccion.invalidMesLiquidacion = true;
					vm.nuevaTransaccion.invalidProveedor = true;
					vm.nuevaTransaccion.invalidCaja = true;	
				}
			} else {
				this.mostrarError('El monto ingresado debe ser mayor a 0');
				vm.nuevaTransaccion.invalidMonto = true;
			}
		},
		restablecerFormPagos: function(){
			vm.nuevaTransaccion.monto = 0;
			vm.nuevaTransaccion.invalidMonto = false;
			vm.nuevaTransaccion.errorMonto = false;
			vm.nuevaTransaccion.gastosComunes = '';
			vm.nuevaTransaccion.fondoReserva = '';
			vm.nuevaTransaccion.fondoCalefaccion = '';
			vm.nuevaTransaccion.fondoCochera = '';
			vm.nuevaTransaccion.fondoObras = '';
			vm.nuevaTransaccion.saldoActual = 0;
			vm.nuevaTransaccion.fecha = null;
			vm.nuevaTransaccion.nroFactura = '';
			vm.nuevaTransaccion.dejareditarpagos = false;
			vm.nuevaTransaccion.cobrarRecargo = false;
			vm.nuevaTransaccion.tipoCaja = {
				id: 1,
				label: 'Gastos Comunes',
				nombre: 'Gastos Comunes',
				value: 1
			};
			vm.nuevaTransaccion.unidad = {};
			vm.nuevaTransaccion.proveedor = {};
			vm.nuevaTransaccion.mesLiquidacion = {
					anio: 0,
					label: '',
					mes: 0,
					mesString: '',
					value: 0
			};
			vm.nuevaTransaccion.observacion = '';
			vm.nuevaTransaccion.debePagarFR = '';
			vm.nuevaTransaccion.debePagarGC = '';
			vm.nuevaTransaccion.debePagarFO = '';
			vm.nuevaTransaccion.debePagarCH = '';
			vm.nuevaTransaccion.debePagarFC = '';
			vm.nuevaTransaccion.habilitarRecargo = false;
			vm.nuevaTransaccion.invalidFecha = false;
			vm.nuevaTransaccion.invalidMonto = false;
			vm.nuevaTransaccion.invalidMesLiquidacion = false;
			vm.nuevaTransaccion.invalidProveedor = false;
			vm.nuevaTransaccion.invalidCaja = false;
			vm.cantClickRec = 0;
		},
		mostrarDetalle: function(){
			mui.viewport.showPage('detalle-edificio-page', 'SLIDE_UP');
		},
		verEstadoCuenta: function(){
			mui.viewport.showPage('estado-cuenta-page', 'SLIDE_UP');
		},
		obtenerEstadoCuenta: function(){
			var edificio = 0;
			if(vm.usuario.tipo=='A'){
				edificio = vm.edificio.id;
			} else if(vm.usuario.tipo=='C'){
				edificio = vm.usuario.idEdificio;
			}
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenerestadocuenta',
					token: vm.usuario.token,
					edificio: edificio,
					mesLiquidacion: vm.estadoCuenta.mesLiquidacion.value
				},
				success: function(data){
					if(data.estado == 0){
						vm.estadoCuenta.totales = data.totales;
						vm.estadoCuenta.pagoProveedor = data.pagoProveedor;
						vm.estadoCuenta.pagoCopropietario = data.pagoCopropietario;
						vm.estadoCuenta.mesLiquidacion = data.mesLiquidacion;
						vm.estadoCuenta.gtosComunes = data.gtosComunes;
						vm.estadoCuenta.fondoReserva = data.fondoReserva;
						vm.estadoCuenta.totalMesGC = data.totalMesGC;
						vm.estadoCuenta.totalMesFR = data.totalMesFR;
						vm.estadoCuenta.mostrarPagos = true;
						me.calcularTotalesEstadoCuenta();
					} else if(data.estado == 200){
						mui.busy(false);
						me.mostrarError(data.mensaje);
					} 
					else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		limpiarFormEstadoCuenta: function(){
			vm.estadoCuenta.mesLiquidacio = {
					label: 'Enero 2020',
					value: '1 2020'
				};
			vm.estadoCuenta.mesLiquidacio.gtosComunes = {};
			vm.estadoCuenta.mesLiquidacio.fondoReserva = {};
			vm.estadoCuenta.mesLiquidacio.totales = {};
			vm.estadoCuenta.mesLiquidacio.pagoProveedor = {
				pagosProveedores: [
					{
						proveedor:{
							nombre: ''
						},
						tipoCaja: 0
					}
				],
				totalPagosGC: 0,
				totalPagosFR: 0,
				totalAbonado: 0
			};
			vm.estadoCuenta.mesLiquidacio.totalMesGC = 0;
			vm.estadoCuenta.mesLiquidacio.totalMesFR = 0;
			vm.estadoCuenta.mesLiquidacio.pagoCopropietario = {
				pagosCopropietarios: [
					{
						idUnidad:{
							nroApartamento: ''
						}
					}
				],
				totalPagosCopGC: '',
				totalPagosCopFR: '',
			};
			vm.estadoCuenta.mesLiquidacio.mostrarPagos = false;
			vm.estadoCuenta.mesLiquidacio.totalPagoProv = 0;
			vm.estadoCuenta.mesLiquidacio.mesLiquidacion = {}
		},
		calcularTotalesEstadoCuenta: function(){
//			var totalPagoProv = 0;
//			for(var i = 0; i < vm.estadoCuenta.pagosProveedores.length; i++){
//				totalPagoProv += vm.estadoCuenta.pagosProveedores[i].monto;
//			}
//			vm.estadoCuenta.totalPagoProv = totalPagoProv;
			var totalFR = 0;
			var totalGC = 0;
			
		},
		guardarContacto: function(){
			if(vm.contacto.mensaje.length > 0 && vm.usuario.email.length > 0){
				var me = this;
				var tipo = '';
				var idEdificio = 0;
				var idUnidad = 0;
				if(vm.usuario.rol=='Copropietario'){
					idEdificio = vm.usuario.idEdificio;
					idUnidad = vm.usuario.idUnidad;
					tipo = 'C';
				} else {
					idUnidad = vm.contacto.unidad.value;
					idEdificio = vm.contacto.edificio.value;
					tipo = "A";
				}
				mui.busy(true);
				mui.ajax({
					url: servicioURL,
					type: 'POST',
					data: {
						comando: 'nuevocontacto',
						token: vm.usuario.token,
						unidad: idUnidad,
						edificio: idEdificio,
						nombre: vm.usuario.usuario,
						telefono: vm.usuario.telefono,
						email: vm.usuario.email,
						texto: vm.contacto.mensaje,
						tipoUsuario: tipo
					},
					success: function(data){
						if(data.estado == 0){
							me.mostrarMensaje(data.mensaje, true, false);
						} else if(data.estado == 400){
							mui.busy(false);
							me.goLogaut();
						} else {
							me.mostrarError(data.mensaje);
						}
						mui.busy(false);
					},
					error: function(err,estado, error){
						mui.busy(false);
						me.mostrarError('Error al intentar comunicarse con el servidor');
					}				
				})
			} else{
				this.mostrarError('Debe ingresar un mensaje y un email de contacto');
			}
			
		},
		descargarEstadoCuenta: function(){
			mui.busy(true);
			window.open('/AdministracionJuncal/GeneradorPDF?edificio=' + vm.edificio.id + '&mes=' + vm.estadoCuenta.mesLiquidacion.mes + '&año=' + vm.estadoCuenta.mesLiquidacion.año);
			mui.busy(false);
		},
		limpiarMensajes: function(){
			vm.selMensaje.edificio ={
				label: '',
				value: 0
			};
			vm.selMensaje.unidad = {
					label: '',
					value: 0
				};
			vm.selMensaje.leeido = {
					label: 'Todos',
					value: 0
				};
			vm.selMensaje.fechaInicio = null;
			vm.selMensaje.ferchaFIn = null;
		},
		obtenerMensajes: function(comando){
			var fechaInicio = '';
			var fechaFin = '';
			var mesI = 0;
			var mesF = 0;
			var idUnidad = 0;
			if(vm.usuario.rol=='Copropietario'){
				idUnidad = vm.usuario.idUnidad
			}
			if(vm.selMensaje.fechaInicio != null){
				if((vm.selMensaje.fechaInicio.getMonth() + 1) < 10){
					mesI = '0' + (vm.selMensaje.fechaInicio.getMonth() + 1);
				} else {
					mesI = vm.selMensaje.fechaInicio.getMonth() + 1;
				}
				fechaInicio =  vm.selMensaje.fechaInicio.getDate() + '-' + mesI + '-' + vm.selMensaje.fechaInicio.getFullYear();
			}
			if(vm.selMensaje.fechaFin != null){
				if((vm.selMensaje.fechaFin.getMonth() + 1) < 10){
					mesF = '0' + (vm.selMensaje.fechaFin.getMonth() + 1);
				} else {
					mesF = vm.selMensaje.fechaFin.getMonth() + 1;
				}
				fechaFin = vm.selMensaje.fechaFin.getDate() + '-' + mesF + '-' + vm.selMensaje.fechaFin.getFullYear();
			}
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenermensajes',
					token: vm.usuario.token,
					tipo: comando,
					edificio: vm.usuario.idEdificio,
					usuario: vm.usuario.usuario,
					leeido: vm.selMensaje.leeido.value,
					edificioP: vm.selMensaje.edificio.value,
					idUnidad: idUnidad,
					fechaInicio: fechaInicio,
					fechaFin: fechaFin,
					rol: vm.usuario.rol
				},
				success: function(data){
					if(data.estado == 0){
						if(data.msjEnviados.length > 0){
							vm.existenMsjEnv = true;
						} else {
							vm.existenMsjEnv = false;
						}
						if(data.msjRecibidos.length > 0){
							vm.existeMsjRec = true;
						} else {
							vm.existeMsjRec = false;
						}
						vm.mensajesEnviados = data.msjEnviados;
						vm.mensajesRecibidos = data.msjRecibidos;
						mui.viewport.showPage('mensajes-page','SLIDE_UP');
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		verMensaje: function(mensaje){
			vm.mensaje = mensaje;
			vm.titulos.detalleMsj = 'Mensaje de ' + mensaje.nombreUsuario;
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'leermensaje',
					token: vm.usuario.token,
					idMensaje: mensaje.id
				},
				success: function(data){
					if(data.estado == 0){
						me.marcarMsjLeeido(mensaje.id);
//						me.ordenarMensajes();
						me.openModal('detalle-mensaje');
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
//		ordenarMensajes: function(){
//			
//				
//			
//		},
		marcarMsjLeeido: function(idMensaje){
			for(var i = 0; i < vm.buzonMensajes.length; i++){
				if(vm.buzonMensajes[i].id == idMensaje){
					vm.buzonMensajes[i].leeido = true;
				}
			}
		},
		verFormularioNuevoEdificio: function(){
			this.openModal('formulario-nuevo-edificio-page');
			vm.cmdEdicionEdificio = 'alta';
		},
		verFormNuevaUnidad: function(){
			this.openModal('detalle-unidad');
			vm.comandoUnidad = 'nuevo';
		},
		agregarNuevaUnidad: function(){
			this.closeModal('detalle-unidad');
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'nuevaunidad',
					token: vm.usuario.token,
					idUnidad: vm.unidad.id,
					apto: vm.unidad.nroApartamento,
					metraje: vm.unidad.metraje,
					idEdificio: vm.edificio.id,
					coeficiente: vm.unidad.coeficiente,
					cantidadCocheras: vm.unidad.cantidadCocheras.value,
					habilitado: vm.unidad.habilitadoTxt.value,
					tipoUnidad: vm.unidad.tipoUnidadTxt.value
				},
				success: function(data){
					if(data.estado == 0){
						me.limpiarFormUnidad();
						me.mostrarMensaje(data.mensaje, true, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		limpiarFormUnidad: function(){
			vm.unidad.id = '';
			vm.unidad.nroApartamento = '';
			vm.unidad.metraje = '';
			vm.unidad.coeficiente = 0,
			vm.unidad.cantidadCocheras = {
					label: '0',
					value: '0'
			};
			vm.unidad.habilitadoTxt = {
					label: 'Si',
					value: 1
			};
			vm.unidad.tipoUnidadTxt = {
					tipoUnidadTxt: {
						label: 'Propietario',
						value: 'P'
					}
			}
		},
		verFormModificarEdificio: function(){
			vm.titulos.detalleEdificio = 'Infomacion de ' + vm.edificio.nombre;
			this.openModal('form-info-edificio');
		},
		modificarInfoEdificio: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'modificaredificio',
					token: vm.usuario.token,
					idEdificio: vm.edificio.id,
					nombre: vm.edificio.nombre,
					direccion: vm.edificio.direccion,
					barrio: vm.edificio.barrio,
					anioConstruccion: vm.edificio.anioConstruccion,
					metrosConstruidos: vm.edificio.metrosConstruidos,
					metrosTotales: vm.edificio.metrosTotales
				},
				success: function(data){
					if(data.estado == 0){
						me.mostrarMsj(data.mensaje);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		verMontosAnuales: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenermontosfijados',
					token: vm.usuario.token,
					idEdificio: vm.edificio.id
				},
				success: function(data){
					if(data.estado == 0){
						if(data.montosFijados.length > 0){
							vm.existeMontos = true;
						} else {
							vm.existeMontos = false;
						}
						vm.montosFijados = data.montosFijados;
						mui.viewport.showPage('historial-montos-page','SLIDE_UP');
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		verFormNuevoMontoFijo: function(){
			this.openModal('formulario-nuevo-monto');
		},
		guardarNuevoMontoFijo: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'guardarnuevomontofijo',
					token: vm.usuario.token,
					edificio: vm.edificio.id,
					anio: vm.nuevoMontoFijo.anio.value,
					montoGC: vm.nuevoMontoFijo.montoGC,
					montoFR: vm.nuevoMontoFijo.montoFR,
					montoFC: vm.nuevoMontoFijo.montoFC,
					montoFO: vm.nuevoMontoFijo.montoFO,
					montoCH: vm.nuevoMontoFijo.montoCH
				},
				success: function(data){
					if(data.estado == 0){
						me.verMontosAnuales();
						me.closeModal('formulario-nuevo-monto');
						me.mostrarMsj(data.mensaje, false, false, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError(data.mensaje);
				}				
			})
		},
		VerDetalleMontoFijado: function(monto){
			vm.detalleMontoFijo = monto;
			this.openModal('detalle-monto-fijo');
		},
		modificarMontosFijados: function(){
			var montoGC = Number.parseInt(vm.detalleMontoFijo.montoGC);
			var montoFR = Number.parseInt(vm.detalleMontoFijo.montoFR);
			var montoFC = Number.parseInt(vm.detalleMontoFijo.montoFC);
			var montoFO = Number.parseInt(vm.detalleMontoFijo.montoFO);
			var montoCH = Number.parseInt(vm.detalleMontoFijo.montoCH);
			
			if(montoGC >= 0 && montoFR >= 0 && montoFC >= 0 && montoFO >= 0 && montoCH >= 0){
				var me = this;
				mui.busy(true);
				mui.ajax({
					url: servicioURL,
					type: 'POST',
					data: {
						comando: 'modificarmontofijado',
						token: vm.usuario.token,
						edificio: vm.edificio.id,
						anio: vm.detalleMontoFijo.anio,
						montoGC: vm.detalleMontoFijo.montoGC,
						montoFR: vm.detalleMontoFijo.montoFR,
						montoFC: vm.detalleMontoFijo.montoFC,
						montoFO: vm.detalleMontoFijo.montoFO,
						montoCH: vm.detalleMontoFijo.montoCH
					},
					success: function(data){
						if(data.estado == 0){
							vm.detalleMontoFijo.invalidGC = false;
							vm.detalleMontoFijo.invalidFR = false;
							vm.detalleMontoFijo.invalidFO = false;
							vm.detalleMontoFijo.invalidFC = false;
							vm.detalleMontoFijo.invalidCH = false;
							me.verMontosAnuales();
							me.closeModal('detalle-monto-fijo');
							me.mostrarMsj(data.mensaje, false, false, false);
						} else if(data.estado == 400){
							mui.busy(false);
							me.goLogaut();
						} else {
							me.mostrarError(data.mensaje);
						}
						mui.busy(false);
					},
					error: function(err,estado, error){
						mui.busy(false);
						me.mostrarError(data.mensaje);
					}				
				})
			} else {
				vm.detalleMontoFijo.invalidGC = true;
				vm.detalleMontoFijo.invalidFR = true;
				vm.detalleMontoFijo.invalidFO = true;
				vm.detalleMontoFijo.invalidFC = true;
				vm.detalleMontoFijo.invalidCH = true;
			}
		},
		verFormularioNuevoMontoGC: function(comando){
			this.openModal('nuevo-monto-fijo');
			vm.tipoFormMontoFijo = comando;
		},
		ingresarNuevoMontoFijo: function(){
			var tipoCaja = 0;
			if(vm.tipoFormMontoFijo=='gc'){
				tiopCaja = 1;
			} else if(vm.tipoFormMontoFijo=='fr'){
				tiopCaja = 2;
			} else if(vm.tipoFormMontoFijo=='fc'){
				tiopCaja = 3;
			} else if(vm.tipoFormMontoFijo=='fo'){
				tiopCaja = 4;
			}
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'guardarmontofijo',
					token: vm.usuario.token,
					edificio: vm.edificio.id,
					anio: vm.nuevoAnioMonto.anio,
					monto: nuevoAnioMonto.monto,
					tipoCaja: tipoCaja
				},
				success: function(data){
					if(data.estado == 0){
						me.mostrarMensaje(data.mensaje, true, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		verFormularioUnidades: function(){
			vm.unidades = vm.edificio.unidades;
			mui.viewport.showPage('fomulario-unidades-page','SLIDE_UP');
		},
		eliminarUnidad: function(unidad){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'eliminarunidad',
					id: vm.unidad.id,
					token: vm.usuario.token
				},
				success: function(data){
					if(data.estado == 0){
						me.mostrarMsj(data.mensaje, false, false, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		verDetalleUnidad: function(unidad){
			vm.unidad = unidad;
			vm.unidad.cantidadCocheras = unidad.cantidadCocheras;
			vm.comandoUnidad = 'editar';
			vm.titulos.detalleUnidad = 'Unidad ' + unidad.label;
			this.openModal('detalle-unidad');
		},
		editarUnidad: function(){
			this.closeModal('detalle-unidad');
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'editarunidad',
					id: vm.unidad.id,
					token: vm.usuario.token,
					apto: vm.unidad.nroApartamento,
					metraje: vm.unidad.metraje,
					coeficiente: vm.unidad.coeficiente,
					cantidadCocheras: vm.unidad.cantidadCocheras.value,
					habilitado: vm.unidad.habilitadoTxt.value,
					tipoUnidad: vm.unidad.tipoUnidadTxt.value
				},
				success: function(data){
					if(data.estado == 0){
						me.mostrarMsj(data.mensaje, false, false, false);
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		mostrarFormularioNuevoUsuario: function(){
			mui.viewport.showPage('fomulario-usuario-page','SLIDE_UP');
		},
		abrirOcpcionesDocumento: function(){
			mui.viewport.showPage('mis-archivos-page','SLIDE_UP');
//			this.openModal('opciones-documentos');
		},
		verFormularioEdicioEdificio: function(){
			mui.viewport.showPage('formulario-edificio-page','SLIDE_UP');
			vm.cmdEdicionEdificio = 'edicion';
		},
		ingresarNuevoUsuario: function(){
			if(vm.nuevoUsuario.usuario.length > 0 && vm.nuevoUsuario.nombre.length > 0 && vm.nuevoUsuario.apellido.length > 0){
				var me = this;
				mui.busy(true);
				mui.ajax({
					url: servicioURL,
					type: 'POST',
					data: {
						comando: 'nuevousuario',
						token: vm.usuario.token,
						usuario: vm.nuevoUsuario.usuario,
						mail2: vm.nuevoUsuario.mail2,
						edificio: vm.nuevoUsuario.edificio.value,
						unidad: vm.nuevoUsuario.unidad.value,
						nombre: vm.nuevoUsuario.nombre,
						apellido: vm.nuevoUsuario.apellido,
						telefono: vm.nuevoUsuario.telefono,
						observaciones: vm.nuevoUsuario.observaciones
					},
					success: function(data){
						if(data.estado == 0){
							vm.nuevoUsuario.invalidUsuario = false;
							vm.nuevoUsuario.invalidNombre = false;
							vm.nuevoUsuario.invalidEdificio = false;
							vm.nuevoUsuario.invalidUnidad = false;
							vm.nuevoUsuario.invalidApellido = false;
							me.mostrarMsj(data.mensaje, true, false, false);
						} else if(data.estado == 400){
							mui.busy(false);
							me.goLogaut();
						} else {
							me.mostrarError(data.mensaje);
						}
						mui.busy(false);
					},
					error: function(err,estado, error){
						mui.busy(false);
						me.mostrarError('Error al intentar comunicarse con el servidor');
					}				
				})
			} else {
				this.mostrarError('Debe ingresar todos los datos solicitados');
				vm.nuevoUsuario.invalidUsuario = true;
				vm.nuevoUsuario.invalidNombre = true;
				vm.nuevoUsuario.invalidEdificio = true;
				vm.nuevoUsuario.invalidUnidad = true;
				vm.nuevoUsuario.invalidApellido = true;
			}
		},
		limpiarFormNuevoUsuario: function(){
			vm.nuevoUsuario.invalidUsuario = false;
			vm.nuevoUsuario.invalidNombre = false;
			vm.nuevoUsuario.invalidEdificio = false;
			vm.nuevoUsuario.invalidUnidad = false;
			vm.nuevoUsuario.invalidApellido = false;
			vm.nuevoUsuario.usuario = '';
			vm.nuevoUsuario.mail = '';
			vm.nuevoUsuario.edificio = {
					label: '',
					value: 0
			}
			vm.nuevoUsuario.unidad = {
					nroApartamento: '',
					tipoUnidad: '',
					label: '',
					value: 0
			}
			vm.nuevoUsuario.nombre = '';
			vm.nuevoUsuario.apellido = '';
			vm.nuevoUsuario.telefono = '';
			vm.nuevoUsuario.observaciones = '';
		},
		obtenerUsuarios: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenerusuarios',
					token: vm.usuario.token,
					idEdificio: vm.selUsuario.edificio.value,
					usuario: vm.selUsuario.nombre,
					tipo: vm.selUsuario.tipo
				},
				success: function(data){
					if(data.estado == 0){
						vm.usuarios = data.usuarios;
						mui.viewport.showPage('usuarios-page','SLIDE_UP');
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		limpiarFormUsuarios: function(){
			vm.selUsuario.edificio = {
					label: '',
					value: 0
			};
			vm.selUsuario.tipo = 'C';
			vm.selUsuario.nombre = '';
		},
		verUsuario: function(usuario){
			vm.detalleUsuario = usuario;
			vm.titulos.detalleUsuario = usuario.usuario;
			this.openModal('detalle-usuario');
		},
		verDetalleCop: function(copropietario){
			vm.detalleCop = copropietario;
			vm.titulos.detalleCop = copropietario.nombre + ' ' + copropietario.apellido;
			this.openModal('detalle-copropietario');
		},
		verListadoCopropietarios: function(){
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'obtenercopropietarios',
					token: vm.usuario.token,
					idEdificio: vm.edificio.id
				},
				success: function(data){
					if(data.estado == 0){
						vm.listaCopropietarios = data.usuarios;
						mui.viewport.showPage('lista-copropietarios-page','SLIDE_UP');
					} else if(data.estado == 400){
						mui.busy(false);
						me.goLogaut();
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		verMisArchivos: function(){
			var fechaInicio = '';
			var fechaFin = '';
			var edificio = 0;
			if(vm.usuario.tipo == 'A'){
				edificio = vm.selDocumento.edificio.value;
			} 
			if(vm.selDocumento.fechaInicio != null){
				fechaInicio = vm.selDocumento.fechaInicio.getFullYear() + '-' + (vm.selDocumento.fechaInicio.getMonth() + 1) + '-' + vm.selDocumento.fechaInicio.getDate();
			}
			if(vm.selDocumento.fechaFin != null){
				fechaFin = vm.selDocumento.fechaFin.getFullYear() + '-' + (vm.selDocumento.fechaFin.getMonth() + 1) + '-' + vm.selDocumento.fechaFin.getDate();
			}
			var me = this;
			mui.busy(true);
			mui.ajax({
				url: servicioURL,
				type: 'POST',
				data: {
					comando: 'verlistaarchivos',
					token: vm.usuario.token,
					fechaInicio: fechaInicio,
					fechaFin: fechaFin,
					edificio: vm.selDocumento.edificio.value
				},
				success: function(data){
					if(data.estado == 0){
						vm.listadoArchivos = data.archivos;
						mui.viewport.showPage('mis-archivos-page','SLIDE_UP');
					} else {
						me.mostrarError(data.mensaje);
					}
					mui.busy(false);
				},
				error: function(err,estado, error){
					mui.busy(false);
					me.mostrarError('Error al intentar comunicarse con el servidor');
				}				
			})
		},
		limparFormArchivos: function(){
			vm.selDocumento.edificio = {
					label: '',
					value: 0
			};
			vm.selDocumento.fechaInicio = null;
			vm.selDocumento.ferchaFIn = null;
		},
		descargarArchivo: function(archivo){
//			var me = this;
//			mui.busy(true);
//			mui.ajax({
//				url: servicioURL,
//				type: 'POST',
//				data: {
//					comando: 'descargararchivo',
//					nombre: archivo.nombre
//				},
//				success: function(data){
//					if(data.estado == 0){
//						alert('exito');
//					} else {
//						me.mostrarError(data.mensaje);
//					}
//					mui.busy(false);
//				},
//				error: function(err,estado, error){
//					mui.busy(false);
//					alert('Error al intentar comunicarse con el servidor');
//				}				
//			})
//			var me = this;
//			try {
//				mui.busy(true);
//				var request = new  XMLHttpRequest();
//				request.open('GET', servicioURL, true);
//				request.setRequestHeader('Content-Type', 'apliccation/x-www-form-urlencoded');
//				request.responseType = 'blob';
//				request.onload = function(e){
//					if(this.status == 200){
//						var file = window.URL.createObjectURL(this.response);
//						var a = document.createElement('A');
//						a.href = file;
//						a.download = 'archivo.pdf';
//						document.body.appendChild(a);
////						a.clikc();
//						document.body.removeChild(a);
//						mui.busy(false);
//					}
//				};
//				request.send('comando=descargararchivo&nombre=' + archivo.nombre);
//			} catch (e) {
//				console.log(e);
//			}
			mui.busy(true);
			window.open('/AdministracionJuncal/GeneradorPDF?nombre=' + archivo.nombre + '&tipo=archivo');
			mui.busy(false);
		},
		verFormNuevaArchivo: function(){
			vm.comandoArchivo = 'documento';
			this.openModal('form-nuevo-archivo');
		},
		verFormNuevoArchivoPago: function(){
			vm.comandoArchivo = 'archivoPago';
			this.openModal('form-nuevo-archivo');
		},
		onAccordionOpen(id) {
            Object.keys(this.accordions).forEach(key => {
                this.accordions[key] = key == id; // eslint-disable-line eqeqeq
            });
        },
        onAccordionClose(key) {
            this.accordions[key] = false;
        },
        cargarDocumento: function(files){
        	vm.documentos = files;
        	this.file11PreviewImage = URL.createObjectURL(files[0]);
        },
        subirArchivoPago: function(){
        	if(vm.documentos.length > 0){
        		mui.busy(true);
            	var me = this;
            	var midoc = vm.documentos[0];
            	var data = new FormData();
            	var filesAux = [];
            	filesAux = vm.documentos;
    			data.append('image', filesAux[0]);
    			data.append('comando', 'subirarchivopago');
    			data.append('token', vm.usuario.token);
    			$.ajax({
    			url: lectorUrl,
    			data: data,
    			cache: false,
            	contentType: false,
            	processData: false,
    			type: 'POST',
    			success: function(data){
    				if(data.estado == 0){
    					vm.nuevoArchivo.invalidNombre = false;
        				vm.nuevoArchivo.invalidArchivo = false;
        				mui.busy(false);
        				me.closeModal('form-nuevo-archivo');
        				me.mostrarMensaje(data.mensaje, true, false);
    				} else {
    					mui.busy(false);
    					me.mostrarError('El sistema no puede procesar el archivo ingresado. Por favor asegurese de que el archivo sea el correcto');
    				}
    				
    			}, error: function(err, status, error) {
        			mui.busy(false);
        			me.mostrarError(data.mensaje);
                }
    			});
        	} else {
        		this.closeModal('form-nuevo-archivo');
        		this.mostrarError('Debe ingresar un archivo Excel');
        	}
        },
        subirDocumento: function(){
        	if(vm.nuevoArchivo.nombre.length > 0 && vm.documentos.length > 0){
        		mui.busy(true);
        		var me = this;
//            	vm.documentos[0].name.replace(/ /g, '');
            	var midoc = vm.documentos[0];
            	var data = new FormData();
            	var filesAux = [];
            	filesAux = vm.documentos;
//            	filesAux[0].name.replace(/ /g, '');
    			data.append('image', filesAux[0]);
    			data.append('comando','subirdocumento');
    			data.append('nombre', vm.nuevoArchivo.nombre);
    			data.append('edificio', vm.nuevoArchivo.edificio.value);
    			data.append('descripcion', vm.nuevoArchivo.descripcion);
    			data.append('token', vm.usuario.token);
    			$.ajax({
    			url: lectorUrl,
    			data: data,
    			cache: false,
            	contentType: false,
            	processData: false,
    			type: 'POST',
    			success: function(data){
    				mui.busy(false);
    				me.closeModal('form-nuevo-archivo');
    				me.limpiarFormNuevoArchivo();
    				me.mostrarMensaje(data.mensaje, true, false);
    			}, error: function(err, status, error) {
        			mui.busy(false);
        			me.closeModal('form-nuevo-archivo');
        			me.mostrarError('Lo sentimos, ocurrió un error al comunicarse con el servidor.');
                }
    			});
        	} else {
        		vm.nuevoArchivo.invalidNombre = true;
        		vm.nuevoArchivo.invalidArchivo = true;
        	}
        },
        limpiarFormNuevoArchivo: function(){
        	vm.nuevoArchivo.nombre = '';
			vm.nuevoArchivo.edificio = {};
			vm.nuevoArchivo.descripcion = '';
			vm.nuevoArchivo.invalidNombre = false;
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
	var me = this;
	mui.busy(true);
	mui.ajax({
		url: servicioURL,
		type: 'POST',
		headers: {
			'Accept':'aplication/json',
			'content-type':'application/x-www-form-urlencoded'
		},
		data: {
			comando: 'iniciosistema',
			usuario: localStorage.getItem('usuario'),
			token: localStorage.getItem('token')
		},
		success: function(data){
			if(data.estado==0){
				vm.usuario = data.usuario;
				vm.proveedores = data.proveedores;
				vm.edificios = data.edificios;
				vm.diaActual = data.diaActual;
//				vm.transacciones = data.transacciones;
//				vm.mesesLiquidacionEdificio = data.mesesEdificio;
				vm.mesesLiquidacion = data.mesesLiquidacion;
				vm.tiposCajas = data.tiposCajas;
				vm.tiposPagos = data.tiposPagos;
				vm.tiposContacto = data.tiposContacto;
				vm.menuOptions = data.itemsMenu;
				vm.showLeftPanel = true;
				mui.screen.showPage('mui-screen-page' , 'NONE');
				mui.busy(false);
			} else {
				mui.busy(false);
				me.mostrarError(data.mensaje);
			}
		},
		error: function(err,estado, error){
			mui.busy(false);
			me.mostrarError('Error al intentar comunicarse con el servidor');
		}
	});
}