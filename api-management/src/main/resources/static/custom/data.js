var servicioURL = '/AdministracionJuncal/AppServicio';
var lectorUrl = '/AdministracionJuncal/FileUploadServlet';

var globalDataVue = {
	menuOptions: [
		 {
	        id: '',
	        label: '',
	        icon: '',
	        mostrar: ''
	    }
	],
	calendarioEsp: {
		months:{
			full: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Setiembre', 'Octubre', 'Noviembre', 'Diciembre'],
			abbreviated: ['Ene', 'Feb','Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Set', 'Oct', 'Nov', 'Dic']
		},
		days: {
			full: ['Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'],
			abbreviated: ['Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
			initials: ['D', 'L', 'M', 'M', 'J', 'V', 'S']
		}
	},
	opcionesUsuario: [
		{
			id: 1,
			label: 'Cambiar contraseña'
		},
		{
			id: 2,
			label: 'Salir del sistema'
		}
	],
	diaActual: 0,
	fechaActual: '',
	showLeftPanel: false,
	isShowAlert: false,
	isLeftPanelCollapsed: false,
	isRightPanelCollapsed: false,
	habilitarVolver: false,
	login:{
		userName: '',
		password: '',
		logueado: false,
		invalidName: false,
		invalidPassword: false
	},
	usuario: {
		usuario: '',
		nombre: '',
		apellido: '',
		email: '',
		tipo: '',
		rol: '',
		logueado: false
	},
	file2: {
		size: 'normal',
        file11PreviewImage: ''
	},
	 accordions: {
         0: true,
         1: false,
    },
	titulo: 'Mi titulo',
	selectProveedor: '',
	mesesLiquidacionEdificio: [],
	mesesLiquidacion: [],
	tiposCajas: [
		{
			id: 0,
			label: '',
			nombre: '',
			value: 0
		}
	],
	tiposPagos: [],
	tipoFormMontoFijo: '',
	nuevoProveedor:  {
		nombre: '',
		rut: '',
		descripcion: ''
	},
	proveedores: [
		{
			id: 0,
			nombre: '',
			descripcion: '',
			rut: ''
		}
	],
	edificios: [
		{
			id: 0,
			nombre: '',
			direccion: ''
		}
	],
	edificio: {
		id: 0,
		nombre: '',
		direccion: '',
		unidades: []
	},
	unidades: [
		{
			coeficiente: 0,
			edificio: 0,
			id: 0,
			label: '',
			montoFR: 0,
			montoGC: 0,
			nroApartamento: '',
			saldoActual: 0,
			saldoAnterior: 0,
			tipoUnidad: '',
			value: 0,
			hablilitado: '',
			tipoUnidadTxt: '',
			hablilitadoTxt: ''
		}
	],
	comandoUnidad: '',
	unidad: {
		coeficiente: 0,
		edificio: 0,
		id: 0,
		label: '',
		montoFR: 0,
		montoGC: 0,
		nroApartamento: '',
		saldoActual: 0,
		saldoAnterior: 0,
		tipoUnidad: '',
		value: 0,
		hablilitado: '',
		cantidadCocheras: {
			label: '0',
			value: 0
		},
		habilitadoTxt: {
			label: 'Si',
			value: 1
		},
		tipoUnidadTxt: {
			label: 'Propietario',
			value: 'P'
		}
	},
	tiposUnidades: [
		{
			label: 'Propietario',
			value: 'P'
		},
		{
			label: 'Inquilino',
			value: 'I'
		},
		{
			label: 'Ambos',
			value: 'A'
		}
	],
	unidadesLista: [
		{
			value: 0,
			label: ''
		}
	],
	listaCopropietarios: [
		
	],
	usuarioCop: {
		
	},
	mensajeModal: {
		title: '',
		mensaje: '',
		cancelar: false,
		confirm: false,
		accept: false,
	},
	mensaje: {
		fecha: '',
		nombreUsuario: ''
	},
	titulos: {
		detalleMsj: '',
		detalleUnidad: '',
		detalleUsuario: '',
		detalleCop: '',
		listaUsuarios: '',
		detalleEdificio: '',
	},
	mensajesEnviados: [],
	mensajesRecibidos: [],
	existenMsjEnv: false,
	existeMsjRec: false,
	buzonMensajes: [],
	tablaTransacciones: {
		unidad: false,
		proveedor: false
	},
	existenTransacciones: false,
	transacciones: [
		{
			edificio: {
				
			},
			unidad: {
				nroApartamento: '',
				tipoUnidad: ''
			},
			proveedor: {
				nombre: ''
			},
			monto: '',
			fecha: '',
			nroFactura: ''
		}
	],
	cantClickRec: 0,
	nuevaTransaccion: {
		monto: 0,
		montoAux: 0,
		montoSugerido: '',
		invalidMonto: false,
		msjMonto: '',
		gastosComunes: '',
		fondoReserva: '',
		gastosComunesAux: '',
		fondoReservaAux: '',
		saldoActual: '',
		saldoActualAux: '',
		saldoAnterior: '',
		fecha: null,
		nroFactura: '',
		dejareditarpagos: false,
		tipoCaja: {
			id: 1,
			label: 'Gastos Comunes',
			nombre: 'Gastos Comunes',
			value: 1
		},
		fondoCalefaccion: '',
		fondoObras: '',
		fondoCochera: '',
		fondoCalefaccionAux: '',
		fondoObrasAux: '',
		fondoCocheraAux: '',
		debePagarFC: 0,
		debePagarCH: 0,
		debePagarFO: 0,
		debePagarFCAux: 0,
		debePagarCHAux: 0,
		debePagarFOAux: 0,
		habilitarRecargo: false,
		cobrarRecargo: false,
		invalidFecha: false,
		invalidMonto: false,
		invalidMesLiquidacion: false,
		invalidCaja: false,
		invalidProveedor: false,
		recargo: 0,
		recargoAux: 0,
		unidad:{
			
		},
		proveedor: {
			
		},
		mesLiquidacion: {
			anio: 0,
			label: '',
			mes: 0,
			mesString: '',
			value: 0
		},
		observacion: '',
		debePagarGC: '',
		debePagarFR: '',
		debePagarGCAux: '',
		debePagarFRAux: ''
	},
	filtrosPagos:{
		edificio: {
			id: 0,
			nombre: '',
			direccion: '',
			unidades: []
		},
		proveedor:{
			id: 0,
			nombre: '',
			descripcion: '',
			rut: ''
		},
		tipoTransaccion: {
			label: 'Copropietario',
			value: 1
		},
		fechaInicio: null,
		fechaFin: null,
		unidad: {
			value: 0
		},
		enRedCobranza: false
		
	},
	usuarioRes: {
		usuario: ''
	},
	nuevoArchivo: {
		edificio: {
			
		},
		nombre: '',
		descripcion: '',
		invalidNombre: false,
		invalidArchivo: false
	},
	comandoArchivo: '',
	listadoArchivos: [
		 {
			 fecha: '',
			 titulo: '',
			 descripcion: '',
			 nombre: ''
		 }
	],
	cmdEdicionEdificio: '',
	estadoCuenta: {
		mesLiquidacion: {
			label: 'Enero 2020',
			value: '1 2020'
		},
		gtosComunes: {
			
		},
		fondoReserva: {
			
		},
		totales: {},
		pagoProveedor: {
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
		},
		totalMesGC: 0,
		totalMesFR: 0,
		pagoCopropietario: {
			pagosCopropietarios: [
				{
					idUnidad:{
						nroApartamento: ''
					}
				}
			],
			totalPagosCopGC: '',
			totalPagosCopFR: '',
		},
		mostrarPagos: false,
		totalPagoProv: 0,
		mesLiquidacion: {
			
		}
	},
	detalleMontoFijo: {
		error: false,
		montoGC: 0,
		montoFR: 0,
		montoFC: 0,
		montoFO: 0,
		montoCH: 0,
		invalidGC: true,
		invalidFR: true,
		invalidFC: true,
		invalidFO: true,
		invalidCH: true
	},
	contacto: {
		edificio: {
			id: 0,
			nombre: '',
			direccion: '',
		},
		unidad: {
			value: 0
		},
		nombre: '',
		telefono: '',
		email: '',
		mensaje: '',
		tipoContacto: {
			label: 'Aviso',
			value: 1,
		}
	},
	selMensaje: {
		edificio: {
			label: '',
			value: 0
		},
		leeido: {
			label: 'Todos',
			value: 0
		},
		fechaInicio: null,
		ferchaFIn: null
	},
	selDocumento: {
		edificio: {
			
		},
		fechaInicio: null,
		ferchaFIn: null
	},
	selUsuario: {
		edificio: {
			
		},
		usuario: '',
		tipo: 'C'
	},
	confirmacionModal: {
		mensaje: '',
		comando: ''
	},
	detalleUsuario: {
		
	},
	detalleCop: {
		
	},
	tipodePago: {
		pagaGc: false,
		pagaFr: false,
		pagaFc: false,
		pagaFo: false,
		pagaCh: false
	},
	formEdificio: {
		nombre: '',
		direccion: '',
		pagaGc: false,
		pagaFr: false,
		tipoPagoGc: {
			
		},
		tipoPagoFr: {
			
		},
		pagaFc: false,
		pagaFo: false,
		tipoPagoFc: {
			
		},
		tipoPagoFo: {
			
		},
	},
	nuevoUsuario: {
		usuario: '',
		mail2: '',
		edificio: {
			label: '',
			value: 0
		},
		unidad: {
			nroApartamento: '',
			tipoUnidad: '',
			label: '',
			value: 0
		},
		nombre: '',
		apellido: '',
		invalidUsuario: false,
		invalidNombre: false,
		invalidEdificio: false,
		invalidUnidad: false,
		invalidApellido: false
	},
	usuarios: [],
	editorUsuario: false,
	montosFijados: [],
	detalleMontoFijo: {
		
	},
	nuevoMontoFijo: {
		anio: {
			label: '2020',
			value: 2020
		},
		montoGC: '',
		montoFR: '',
		montoFC: '',
		montoCH: '',
		montoFO: ''
	},
	existeMontos: false,
	tiposContacto: [],
	tipoTransacciones: [
		{
			label: 'Copropietario',
			value: 1,
		},
		{
			label: 'Proveedor',
			value: 2
		}
	],
	documentos: [],
	cmdListaUsuarios: '',
	mesCerrado: {
		mesLiquidacion: {
			label: '',
			value: 0
		}
	},
	nuevaLiquidacion: {
		mesLiquidacion: {
			label: 'Julio 2020',
			value: '7 2020'
		}
	},
	nuevoAnioMonto: {
		anio: '',
		monto: '',
		tipoMonto: ''
	},
	opciopnesMsj: [
		{
			label: 'Todos',
			value: 0
		},
		{
			label: 'No leeidos',
			value: 2
		},
		{
			label: 'Leeido',
			value: 1
		}
	],
	opcionesBasicas: [
		{
			label: 'Si',
			value: 1
		},
		{
			label: 'No',
			value: 0
		}
	],
	tiposUsuarios: [
		{
			label: 'Copropietario',
			value: 'C'
		},
		{
			label: 'Administrador',
			value: 'A'
		}
	],
	anios: [
		{
			label: '2019',
			value: 2019
		},
		{
			label: '2020',
			value: 2020
		},
		{
			label: '2021',
			value: 2021
		},
		{
			label: '2022',
			value: 2022
		}
	],
	cantCocheras: [
		{
			label: '0',
			value: 0
		},
		{
			label: '1',
			value: 1
		},
		{
			label: '2',
			value: 2
		},
		{
			label: '3',
			value: 3
		},
		{
			label: '4',
			value: 4
		},
		{
			label: '5',
			value: 5
		},
	]
}