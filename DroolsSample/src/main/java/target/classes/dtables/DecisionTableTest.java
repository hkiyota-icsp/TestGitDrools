package target.classes.dtables;


import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import target.classes.dtables.RequiredMaterials.Message;

public class DecisionTableTest {

    public static void main(String[] args) {
    	System.out.println("BootStrapping the Rule Engine");
    	
    	TestDrl.ExcelToDrl();
    	
        try {
            // load up the knowledge base
        	//呪文。お決まりのやつ
        	KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    //ここのKieSessionはkmodule.xmlで設定
        	KieSession kSession = kContainer.newKieSession("ksession-dtables");

            //必要なクラスのインスタンスを作成して値を入れる
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            
            //kSessionにmessageを挿入
            kSession.insert(message);
            //kSessionのルールの実行
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
