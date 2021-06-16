package org.xpande.logistica.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/16/21.
 */
public class MZPickingProd extends X_Z_PickingProd{

    public MZPickingProd(Properties ctx, int Z_PickingProd_ID, String trxName) {
        super(ctx, Z_PickingProd_ID, trxName);
    }

    public MZPickingProd(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
