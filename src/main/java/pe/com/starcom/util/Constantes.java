package pe.com.starcom.util;

public interface Constantes {

	// roles
	public static final Long ROLE_ADMIN = 1L;
	public static final Long ROLE_PE = 2L;
	public static final Long ROLE_GDGP = 3L;
	public static final Long ROLE_SELEC = 4L;
	public static final Long ROLE_ASIG = 5L;
	public static final Long ROLE_CONV = 6L;
	public static final Long ROLE_MyE = 7L;
	public static final Long ROLE_LOGI = 8L;
	public static final Long ROLE_OAJ = 9L;
	public static final Long ROLE_GG = 10L;
	public static final Long ROLE_TI = 11L;
	public static final Long ROLE_LEG = 12L;

	// estado
	public static final String ACTIVO = "A";
	public static final String INACTIVO = "E";
	public static final String PENDIENTE = "P";
	public static final String EN_ESPERA = "EE";
	public static final String ATENDIDO = "AT";
	public static final String CANCELADO = "C";


	// Procesos
	public static final Long PROCESO1 = 1L;
	public static final Long PROCESO2 = 2L;
	public static final Long PROCESO3 = 3L;

	// Actividades

	public static final Long P1_1_RECEPCIONA_PEDIDO_DE_ENTIDAD = 1L;
	public static final Long P1_2_DERIVA_PEDIDO_A_GDGP = 2L;
	public static final Long P1_3_RECEPCIONA_PEDIDO = 3L;
	public static final Long P1_4_DERIVA_A_MPACHECO = 4L;
	public static final Long P1_5_VERIFICA_CUMPLIMIENTO_DE_REQUISITOS_FORMALES = 5L;
	public static final Long P1_6_REGISTRA_PEDIDO = 6L;
	public static final Long P1_7_BUSCA_RESOLUCION_DE_CARGO_DESTINO = 7L;
	public static final Long P1_8_EXISTE_CARGO_DESTINO = 8L;
	public static final Long P1_9_SELECCIONA_GGPP_CON_EL_PERFIL_SOLICITADO = 9L;
	public static final Long P1_10_PREPARA_PROPUESTA_PARA_ENTIDAD = 10L;
	public static final Long P1_11_ENVIA_PROPUESTA_A_ENTIDAD = 11L;
	public static final Long P1_12_RECEPCIONA_Y_REGISTRA_ELECCION_DE_ENTIDAD = 12L;
	public static final Long P1_13_EXISTE_CONVENIO_MARCO = 13L;
	public static final Long P1_14_CONVENIO_MARCO = 14L;
	public static final Long P1_15_PREPARA_SUSTENTO_CARGO_DESTINO_Y_ENVIA_EN_WORD = 15L;
	public static final Long P1_16_VISA_SUSTENTO_DE_CARGO_DESTINO = 16L;
	public static final Long P1_17_CIRCUITO_DE_RESOLUCION = 17L;
	public static final Long P1_18_PREPARA_POLITICA_REMUNERATIVA = 18L;
	public static final Long P1_19_ENVIA_POLITICA_REMUNERATIVA_A_GGPP = 19L;
	public static final Long P1_20_RECEPCIONA_Y_VALIDA_FORMATOS_DE_ACEPTACION_DEL_GGPP = 20L;
	public static final Long P1_21_PREPARA_INFORME_A_COMITE_DE_GERENTES = 21L;
	public static final Long P1_22_VISA_INFORME = 22L;
	public static final Long P1_23_COMITE_DE_GERENTES = 23L;
	public static final Long P1_24_PREPARA_ACTA_DE_COMITE_DE_GERENTES = 24L;
	public static final Long P1_25_RESPONSABLE_DE_VISADO_DE_ACTAS = 25L;
	public static final Long P1_26_PREPARA_SUSTENTO_DE_ASIGNACION = 26L;
	public static final Long P1_27_REVISA_Y_ENVIA_SUSTENTO_A_CONSEJO_DIRECTIVO = 27L;
	public static final Long P1_28_CIRCUITO_DE_RESOLUCION_2 = 28L;
	public static final Long P1_29_COMUNICA_A_ENTIDAD_PARA_RESOLUCION_DE_DESIGNACION = 29L;
	public static final Long P1_30_PREPARA_ANEXO_02 = 30L;
	public static final Long P1_31_CONVENIO_DE_ASIGNACION = 31L;
	public static final Long P1_32_ENVIA_CONVENIOS_A_SECRETARIA_Y_LOGISTICA = 32L;
	public static final Long P1_33_ARCHIVA_CONVENIO = 33L;
	public static final Long P1_34_SE_ENVIA_CONVENIO_A_GGPP_Y_ENTIDAD = 34L;

	public static final Long P2_1_RECIBE_SUSTENTO = 1L;
	public static final Long P2_2_CONSEJO_DIRECTIVO = 2L;
	public static final Long P2_3_ACTA_DE_CONSEJO_DIRECTIVO = 3L;
	public static final Long P2_4_PROYECTA_RESOLUCIÓN = 4L;
	public static final Long P2_5_VISTO_BUENO = 5L;
	public static final Long P2_6_TRASLADA_DOCUMENTOS = 6L;
	public static final Long P2_7_RECIBE_DOCUMENTOS = 7L;
	public static final Long P2_8_REVISA = 8L;
	public static final Long P2_9_VB = 9L;
	public static final Long P2_10_TRASLADA_DOCUMENTOS = 10L;
	public static final Long P2_11_RECIBE_DOCUMENTOS = 11L;
	public static final Long P2_12_REVISA = 12L;
	public static final Long P2_13_VB = 13L;
	public static final Long P2_14_TRASLADA_DOCUMENTOS = 14L;
	public static final Long P2_15_RECIBE_DOCUMENTOS = 15L;
	public static final Long P2_16_REVISA = 16L;
	public static final Long P2_17_VB_NUMERACIÓN = 17L;
	public static final Long P2_18_REQUIERE_PUBLICAR_EN_EL_PERUANO = 18L;
	public static final Long P2_19_TRASLADA_PARA_QUEMAR_CD = 19L;
	public static final Long P2_20_QUEMA_CD_CON_RESOLUCIÓN_EN_WORD = 20L;
	public static final Long P2_21_TRASLADA_CD_A_GG = 21L;
	public static final Long P2_22_REDACTA_OFICIO_ANEXADO_AL_CD = 22L;
	public static final Long P2_23_ENVIA_OFICIO_CD_AL_PERUANO = 23L;

	public static final Long P2_1_REALIZA_PROYECTO_DE_CONVENIO = 1L;
	public static final Long P2_2_ENVIA_A_GERENTE_GDGP = 2L;
	public static final Long P2_3_REALIZA_PRESICIONES = 3L;
	public static final Long P2_4_ENVIA_CONVENIO_A_ENTIDAD = 4L;
	public static final Long P2_5_SEGUIMIENTO_A_CONVENIO_A_ENTIDAD = 5L;
	public static final Long P2_6_RECEPCIONA_CONVENIO_DE_ENTIDAD = 6L;
	public static final Long P2_7_ENVIA_CONVENIO_A_GGPP = 7L;
	public static final Long P2_8_SEGUIMIENTO_A_CONVENIO_A_GGPP = 8L;
	public static final Long P2_9_RECEPCIONA_CONVENIO_DE_GGPP = 9L;
	public static final Long P2_10_REMITE_A_AREA_LEGAL = 10L;
	public static final Long P2_11_COMPLETA_FECHAS = 11L;
	public static final Long P2_12_VB = 12L;
	public static final Long P2_13_ENVIA_A_OAJ_CON_CARGO = 13L;
	public static final Long P2_14_VB = 14L;
	public static final Long P2_15_ENVIA_A_PE_CON_CARGO = 15L;
	public static final Long P2_16_FIRMA = 16L;

}
