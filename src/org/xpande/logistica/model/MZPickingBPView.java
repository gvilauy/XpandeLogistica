package org.xpande.logistica.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 6/16/21.
 */
public class MZPickingBPView extends X_Z_PickingBPView{

    public MZPickingBPView(Properties ctx, int Z_PickingBPView_ID, String trxName) {
        super(ctx, Z_PickingBPView_ID, trxName);
    }

    public MZPickingBPView(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
