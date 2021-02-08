package target.classes.dtables;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DecisionTableTest {

    public static void main(String[] args) {
    	System.out.println("BootStrapping the Rule Engine");
    	
    	ExcelToDrl();
    	
        try {
            // load up the knowledge base
        	//�����B�����܂�̂��
        	KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    	    //������KieSession��kmodule.xml�Őݒ�
        	KieSession kSession = kContainer.newKieSession("ksession-dtables");

            //���΂�����[�I
            Message message = new Message();
            message.setMessage("Hello World");
            message.setStatus(Message.HELLO);
            kSession.insert(message);
            kSession.fireAllRules();
            
        } catch (Throwable t) {
            t.printStackTrace();
        } 
        
    }

    //�K�v�ȃN���X�BRBE�ɐH�킹���ŕK�v�ȓz�͍��
    public static class Message {

        public static final int HELLO = 0;
        public static final int GOODBYE = 1;

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
    
    //�f�V�W�����e�[�u����DRL�ɕϊ����ăR���\�[���ɕ\��
    //�K�v�ł���΃e�L�X�g�������ŏo�͂���
    public static void ExcelToDrl(){
    	InputStream is = null;
    	InputStream is2 = null;
     	try {
    		is = new FileInputStream("C:\\eclipse\\Drools_sample_rulebase\\DroolsSample\\src\\main\\resources\\dtables\\Sample.xlsx");
    		is2 = new FileInputStream("C:\\eclipse\\Drools_sample_rulebase\\DroolsSample\\src\\main\\resources\\dtables\\Sample2.xlsx");
    	}catch(FileNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	SpreadsheetCompiler sc = new SpreadsheetCompiler();
    	String drl = sc.compile(is, InputType.XLS);
    	String drl2 = sc.compile(is2, InputType.XLS);
    	System.out.println(drl+drl2);
    }
    
}
