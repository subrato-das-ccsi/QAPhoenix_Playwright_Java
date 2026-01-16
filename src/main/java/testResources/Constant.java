package testResources;

import com.consensus.qaauto.phoenix.pageObjects.NavigationBarPage;
import com.microsoft.playwright.Page;

import java.util.Arrays;
import java.util.List;

public class Constant extends NavigationBarPage {

    public static String approverName="";

    public static String username="";

    public static String paymentSchedule="";

    public static String Opportunity="";

    public static String OrderId="";

    public static String QuoteId="";

    public static String discount="";

    public static String quoteId="";

    public static String accountName="";

    public  static String softwareTotal="";

    public  static String maintananceTotal="";

    public  static String outSourcingTotal="";

    public  static String consultingTotal="";

    public  static String grandTotal="";

    public static String emailAddress="";



    public static String assetsSubscribed="";
//for software products
    public static List<String> approversFor10to20PercentDiscount= Arrays.asList("");

    public static List<String> approversFor20to30PercentDiscount=Arrays.asList("");

    public static List<String> approversFor30to50PercentDiscount= Arrays.asList("");

    public static List<String> approversForAbove50PercentDiscount= Arrays.asList("");

    public  static String closeWindow="button[title='Close this window'] svg";
    public static String quotevalidityDate="";
    public static String salesRepName="";

    public static String pdfContent="";

    public static String paymentTerms="The description and charges for the Services are set forth on the attached Service Schedule. Payments\n" +
            "under the Agreement are due Net 30 days from the date of invoice. Customer shall pay for the\n" +
            "Services described on the Service Schedule in accordance with this Agreement. If Customer requires any\n" +
            "professional services offered by Consensus Cloud Solution in connection with the Services, then Consensus\n" +
            "Cloud Solutions and Customer will enter into a separate statement of work or similar document outlining such\n" +
            "services and pricing, which, when executed, will be expressly and fully incorporated into this Agreement";

public static String supportTerms="Annual support fees include both ongoing product enhancements as well as product support.";

    public static String contact="";
    public static boolean pdfTest=false;
    public static boolean originalValue=false;
    public static String companyName="";
    public static String firstName ="";
    public static String lastName ="";
    public static String approverRole="";
    public static String quoteStatus="";
    public static String loggedInProfile="";
    public static String project="";
    public static String additionalNotes="Please let me know if you have any concerns with the updates to place this prior to the Payment Terms\n" +
            "ina section called Additional Notes on the Contract.";
    public static boolean debugMode=false;


    public Constant(Page page) {
        super(page);
    }


}
