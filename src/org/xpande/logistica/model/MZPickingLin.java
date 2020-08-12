package org.xpande.logistica.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modelo para lineas del proceso de Picking de Mercaderias.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/12/20.
 */
public class MZPickingLin extends X_Z_PickingLin {

    public MZPickingLin(Properties ctx, int Z_PickingLin_ID, String trxName) {
        super(ctx, Z_PickingLin_ID, trxName);
    }

    public MZPickingLin(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
