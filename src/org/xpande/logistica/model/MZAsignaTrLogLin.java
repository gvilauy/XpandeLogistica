package org.xpande.logistica.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para lineas del proceso de asignación de transporte en logistica.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/5/20.
 */
public class MZAsignaTrLogLin extends X_Z_AsignaTrLogLin {

    public MZAsignaTrLogLin(Properties ctx, int Z_AsignaTrLogLin_ID, String trxName) {
        super(ctx, Z_AsignaTrLogLin_ID, trxName);
    }

    public MZAsignaTrLogLin(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
