package org.xpande.logistica.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.xpande.comercial.model.MZGeneraEntrega;
import org.xpande.logistica.model.MZAsignaTrLog;

/**
 * Proceso para obtener reservas a considerar en el proceso de asignación de transporte en logística.
 * Product: Adempiere ERP & CRM Smart Business Solution. Localization : Uruguay - Xpande
 * Xpande. Created by Gabriel Vila on 8/6/20.
 */
public class ObtenerResAsignaTr extends SvrProcess {

    private MZAsignaTrLog asignaTrLog = null;
    private String tipoAccion = "NUEVOS";

    @Override
    protected void prepare() {

        ProcessInfoParameter[] para = getParameter();

        for (int i = 0; i < para.length; i++){

            String name = para[i].getParameterName();

            if (name != null){

                if (para[i].getParameter() != null){

                    if (name.trim().equalsIgnoreCase("SelDocGenPagoAction")){
                        this.tipoAccion = (String)para[i].getParameter();
                    }
                }
            }
        }

        this.asignaTrLog = new MZAsignaTrLog(getCtx(), this.getRecord_ID(), get_TrxName());
    }

    @Override
    protected String doIt() throws Exception {

        this.asignaTrLog.getData(tipoAccion);

        return "OK";
    }


}
