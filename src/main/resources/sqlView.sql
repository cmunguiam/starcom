CREATE VIEW PuestoTipo AS
SELECT * FROM ADMINGP2.puesto_tipo ORDER BY id_puesto_tipo;

CREATE VIEW GerentePublico AS
SELECT cod_gerente, nombres, ap_paterno, ap_materno, dni FROM ADMINGP2.GERENTE_PUBLICO ORDER BY ap_paterno asc;

CREATE VIEW EntidadDestino AS
SELECT id_ed, desc_ed FROM ADMINGP2.ENTIDAD_DESTINO ORDER BY desc_ed ASC;
