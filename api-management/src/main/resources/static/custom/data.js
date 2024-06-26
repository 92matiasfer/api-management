var serviceURL = 'http://localhost:8090';
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
	buildings: [

	],
	building: {
		id: 0,
		value: 0,
		name: '',
		label: '',
		address: '',
		units: [
			
		]
	},
	units: [
		
	],
	suppliers: [
		
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
	 accordions: {
         0: true,
         1: false,
    },
	usuarios: [],
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