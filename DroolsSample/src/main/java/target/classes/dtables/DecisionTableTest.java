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
        	//�����B�����܂�̂��
        	KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    //������KieSession��kmodule.xml�Őݒ�
        	KieSession kSession = kContainer.newKieSession("ksession-dtables");

            //�K�v�ȃN���X�̃C���X�^���X���쐬���Ēl������
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            
            //kSession��message��}��
            kSession.insert(message);
            //kSession�̃��[���̎��s
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
