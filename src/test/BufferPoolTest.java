package test;

import edu.db2.BufferPool;
import edu.db2.Frame;
import org.junit.Assert;

import java.awt.print.PrinterAbortException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class BufferPoolTest {

    private ByteArrayOutputStream console = new ByteArrayOutputStream();
    PrintStream consoleOutput = System.out;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.setOut(new PrintStream(console));
    }

    @org.junit.jupiter.api.AfterEach
    void reset(){
        System.setOut(consoleOutput);
    }

    @org.junit.jupiter.api.Test
    void initialize() {
        BufferPool bufferPool = new BufferPool(4);

        Assert.assertTrue(bufferPool.getBuffers().length == 4);
        for(Frame f : bufferPool.getBuffers()){
            Assert.assertTrue(!f.getDirty() && !f.getPinned() & f.getBlockId() == -1);
        }
    }

    @org.junit.jupiter.api.Test
    void GET() {
        BufferPool bufferPool = new BufferPool(3);
        bufferPool.GET(1);
        bufferPool.GET(150);
        bufferPool.GET(300);

        Frame[] bufferFrames = bufferPool.getBuffers();

        Frame frame1 = bufferFrames[0];
        Assert.assertTrue(!frame1.getDirty());
        Assert.assertTrue(!frame1.getPinned());
        Assert.assertTrue(frame1.getBlockId() == 1);
        Assert.assertEquals(new String(frame1.getContent(), StandardCharsets.US_ASCII), "F01-Rec001, Name001, address001, age001.F01-Rec002, Name002, address002, age002.F01-Rec003, Name003, address003, age003.F01-Rec004, Name004, address004, age004.F01-Rec005, Name005, address005, age005.F01-Rec006, Name006, address006, age006.F01-Rec007, Name007, address007, age007.F01-Rec008, Name008, address008, age008.F01-Rec009, Name009, address009, age009.F01-Rec010, Name010, address010, age010.F01-Rec011, Name011, address011, age011.F01-Rec012, Name012, address012, age012.F01-Rec013, Name013, address013, age013.F01-Rec014, Name014, address014, age014.F01-Rec015, Name015, address015, age015.F01-Rec016, Name016, address016, age016.F01-Rec017, Name017, address017, age017.F01-Rec018, Name018, address018, age018.F01-Rec019, Name019, address019, age019.F01-Rec020, Name020, address020, age020.F01-Rec021, Name021, address021, age021.F01-Rec022, Name022, address022, age022.F01-Rec023, Name023, address023, age023.F01-Rec024, Name024, address024, age024.F01-Rec025, Name025, address025, age025.F01-Rec026, Name026, address026, age026.F01-Rec027, Name027, address027, age027.F01-Rec028, Name028, address028, age028.F01-Rec029, Name029, address029, age029.F01-Rec030, Name030, address030, age030.F01-Rec031, Name031, address031, age031.F01-Rec032, Name032, address032, age032.F01-Rec033, Name033, address033, age033.F01-Rec034, Name034, address034, age034.F01-Rec035, Name035, address035, age035.F01-Rec036, Name036, address036, age036.F01-Rec037, Name037, address037, age037.F01-Rec038, Name038, address038, age038.F01-Rec039, Name039, address039, age039.F01-Rec040, Name040, address040, age040.F01-Rec041, Name041, address041, age041.F01-Rec042, Name042, address042, age042.F01-Rec043, Name043, address043, age043.F01-Rec044, Name044, address044, age044.F01-Rec045, Name045, address045, age045.F01-Rec046, Name046, address046, age046.F01-Rec047, Name047, address047, age047.F01-Rec048, Name048, address048, age048.F01-Rec049, Name049, address049, age049.F01-Rec050, Name050, address050, age050.F01-Rec051, Name051, address051, age051.F01-Rec052, Name052, address052, age052.F01-Rec053, Name053, address053, age053.F01-Rec054, Name054, address054, age054.F01-Rec055, Name055, address055, age055.F01-Rec056, Name056, address056, age056.F01-Rec057, Name057, address057, age057.F01-Rec058, Name058, address058, age058.F01-Rec059, Name059, address059, age059.F01-Rec060, Name060, address060, age060.F01-Rec061, Name061, address061, age061.F01-Rec062, Name062, address062, age062.F01-Rec063, Name063, address063, age063.F01-Rec064, Name064, address064, age064.F01-Rec065, Name065, address065, age065.F01-Rec066, Name066, address066, age066.F01-Rec067, Name067, address067, age067.F01-Rec068, Name068, address068, age068.F01-Rec069, Name069, address069, age069.F01-Rec070, Name070, address070, age070.F01-Rec071, Name071, address071, age071.F01-Rec072, Name072, address072, age072.F01-Rec073, Name073, address073, age073.F01-Rec074, Name074, address074, age074.F01-Rec075, Name075, address075, age075.F01-Rec076, Name076, address076, age076.F01-Rec077, Name077, address077, age077.F01-Rec078, Name078, address078, age078.F01-Rec079, Name079, address079, age079.F01-Rec080, Name080, address080, age080.F01-Rec081, Name081, address081, age081.F01-Rec082, Name082, address082, age082.F01-Rec083, Name083, address083, age083.F01-Rec084, Name084, address084, age084.F01-Rec085, Name085, address085, age085.F01-Rec086, Name086, address086, age086.F01-Rec087, Name087, address087, age087.F01-Rec088, Name088, address088, age088.F01-Rec089, Name089, address089, age089.F01-Rec090, Name090, address090, age090.F01-Rec091, Name091, address091, age091.F01-Rec092, Name092, address092, age092.F01-Rec093, Name093, address093, age093.F01-Rec094, Name094, address094, age094.F01-Rec095, Name095, address095, age095.F01-Rec096, Name096, address096, age096.F01-Rec097, Name097, address097, age097.F01-Rec098, Name098, address098, age098.F01-Rec099, Name099, address099, age099.F01-Rec100, Name100, address100, age100.");

        Frame frame2 = bufferFrames[1];
        Assert.assertTrue(!frame2.getDirty());
        Assert.assertTrue(!frame2.getPinned());
        Assert.assertTrue(frame2.getBlockId() == 2);
        Assert.assertEquals(new String(frame2.getContent(), StandardCharsets.US_ASCII), "F02-Rec101, Name101, address101, age101.F02-Rec102, Name102, address102, age102.F02-Rec103, Name103, address103, age103.F02-Rec104, Name104, address104, age104.F02-Rec105, Name105, address105, age105.F02-Rec106, Name106, address106, age106.F02-Rec107, Name107, address107, age107.F02-Rec108, Name108, address108, age108.F02-Rec109, Name109, address109, age109.F02-Rec110, Name110, address110, age110.F02-Rec111, Name111, address111, age111.F02-Rec112, Name112, address112, age112.F02-Rec113, Name113, address113, age113.F02-Rec114, Name114, address114, age114.F02-Rec115, Name115, address115, age115.F02-Rec116, Name116, address116, age116.F02-Rec117, Name117, address117, age117.F02-Rec118, Name118, address118, age118.F02-Rec119, Name119, address119, age119.F02-Rec120, Name120, address120, age120.F02-Rec121, Name121, address121, age121.F02-Rec122, Name122, address122, age122.F02-Rec123, Name123, address123, age123.F02-Rec124, Name124, address124, age124.F02-Rec125, Name125, address125, age125.F02-Rec126, Name126, address126, age126.F02-Rec127, Name127, address127, age127.F02-Rec128, Name128, address128, age128.F02-Rec129, Name129, address129, age129.F02-Rec130, Name130, address130, age130.F02-Rec131, Name131, address131, age131.F02-Rec132, Name132, address132, age132.F02-Rec133, Name133, address133, age133.F02-Rec134, Name134, address134, age134.F02-Rec135, Name135, address135, age135.F02-Rec136, Name136, address136, age136.F02-Rec137, Name137, address137, age137.F02-Rec138, Name138, address138, age138.F02-Rec139, Name139, address139, age139.F02-Rec140, Name140, address140, age140.F02-Rec141, Name141, address141, age141.F02-Rec142, Name142, address142, age142.F02-Rec143, Name143, address143, age143.F02-Rec144, Name144, address144, age144.F02-Rec145, Name145, address145, age145.F02-Rec146, Name146, address146, age146.F02-Rec147, Name147, address147, age147.F02-Rec148, Name148, address148, age148.F02-Rec149, Name149, address149, age149.F02-Rec150, Name150, address150, age150.F02-Rec151, Name151, address151, age151.F02-Rec152, Name152, address152, age152.F02-Rec153, Name153, address153, age153.F02-Rec154, Name154, address154, age154.F02-Rec155, Name155, address155, age155.F02-Rec156, Name156, address156, age156.F02-Rec157, Name157, address157, age157.F02-Rec158, Name158, address158, age158.F02-Rec159, Name159, address159, age159.F02-Rec160, Name160, address160, age160.F02-Rec161, Name161, address161, age161.F02-Rec162, Name162, address162, age162.F02-Rec163, Name163, address163, age163.F02-Rec164, Name164, address164, age164.F02-Rec165, Name165, address165, age165.F02-Rec166, Name166, address166, age166.F02-Rec167, Name167, address167, age167.F02-Rec168, Name168, address168, age168.F02-Rec169, Name169, address169, age169.F02-Rec170, Name170, address170, age170.F02-Rec171, Name171, address171, age171.F02-Rec172, Name172, address172, age172.F02-Rec173, Name173, address173, age173.F02-Rec174, Name174, address174, age174.F02-Rec175, Name175, address175, age175.F02-Rec176, Name176, address176, age176.F02-Rec177, Name177, address177, age177.F02-Rec178, Name178, address178, age178.F02-Rec179, Name179, address179, age179.F02-Rec180, Name180, address180, age180.F02-Rec181, Name181, address181, age181.F02-Rec182, Name182, address182, age182.F02-Rec183, Name183, address183, age183.F02-Rec184, Name184, address184, age184.F02-Rec185, Name185, address185, age185.F02-Rec186, Name186, address186, age186.F02-Rec187, Name187, address187, age187.F02-Rec188, Name188, address188, age188.F02-Rec189, Name189, address189, age189.F02-Rec190, Name190, address190, age190.F02-Rec191, Name191, address191, age191.F02-Rec192, Name192, address192, age192.F02-Rec193, Name193, address193, age193.F02-Rec194, Name194, address194, age194.F02-Rec195, Name195, address195, age195.F02-Rec196, Name196, address196, age196.F02-Rec197, Name197, address197, age197.F02-Rec198, Name198, address198, age198.F02-Rec199, Name199, address199, age199.F02-Rec200, Name200, address200, age200.");

        Frame frame3 = bufferFrames[2];
        Assert.assertTrue(!frame3.getDirty());
        Assert.assertTrue(!frame3.getPinned());
        Assert.assertTrue(frame3.getBlockId() == 3);
        Assert.assertEquals(new String(frame3.getContent(), StandardCharsets.US_ASCII), "F03-Rec201, Name201, address201, age201.F03-Rec202, Name202, address202, age202.F03-Rec203, Name203, address203, age203.F03-Rec204, Name204, address204, age204.F03-Rec205, Name205, address205, age205.F03-Rec206, Name206, address206, age206.F03-Rec207, Name207, address207, age207.F03-Rec208, Name208, address208, age208.F03-Rec209, Name209, address209, age209.F03-Rec210, Name210, address210, age210.F03-Rec211, Name211, address211, age211.F03-Rec212, Name212, address212, age212.F03-Rec213, Name213, address213, age213.F03-Rec214, Name214, address214, age214.F03-Rec215, Name215, address215, age215.F03-Rec216, Name216, address216, age216.F03-Rec217, Name217, address217, age217.F03-Rec218, Name218, address218, age218.F03-Rec219, Name219, address219, age219.F03-Rec220, Name220, address220, age220.F03-Rec221, Name221, address221, age221.F03-Rec222, Name222, address222, age222.F03-Rec223, Name223, address223, age223.F03-Rec224, Name224, address224, age224.F03-Rec225, Name225, address225, age225.F03-Rec226, Name226, address226, age226.F03-Rec227, Name227, address227, age227.F03-Rec228, Name228, address228, age228.F03-Rec229, Name229, address229, age229.F03-Rec230, Name230, address230, age230.F03-Rec231, Name231, address231, age231.F03-Rec232, Name232, address232, age232.F03-Rec233, Name233, address233, age233.F03-Rec234, Name234, address234, age234.F03-Rec235, Name235, address235, age235.F03-Rec236, Name236, address236, age236.F03-Rec237, Name237, address237, age237.F03-Rec238, Name238, address238, age238.F03-Rec239, Name239, address239, age239.F03-Rec240, Name240, address240, age240.F03-Rec241, Name241, address241, age241.F03-Rec242, Name242, address242, age242.F03-Rec243, Name243, address243, age243.F03-Rec244, Name244, address244, age244.F03-Rec245, Name245, address245, age245.F03-Rec246, Name246, address246, age246.F03-Rec247, Name247, address247, age247.F03-Rec248, Name248, address248, age248.F03-Rec249, Name249, address249, age249.F03-Rec250, Name250, address250, age250.F03-Rec251, Name251, address251, age251.F03-Rec252, Name252, address252, age252.F03-Rec253, Name253, address253, age253.F03-Rec254, Name254, address254, age254.F03-Rec255, Name255, address255, age255.F03-Rec256, Name256, address256, age256.F03-Rec257, Name257, address257, age257.F03-Rec258, Name258, address258, age258.F03-Rec259, Name259, address259, age259.F03-Rec260, Name260, address260, age260.F03-Rec261, Name261, address261, age261.F03-Rec262, Name262, address262, age262.F03-Rec263, Name263, address263, age263.F03-Rec264, Name264, address264, age264.F03-Rec265, Name265, address265, age265.F03-Rec266, Name266, address266, age266.F03-Rec267, Name267, address267, age267.F03-Rec268, Name268, address268, age268.F03-Rec269, Name269, address269, age269.F03-Rec270, Name270, address270, age270.F03-Rec271, Name271, address271, age271.F03-Rec272, Name272, address272, age272.F03-Rec273, Name273, address273, age273.F03-Rec274, Name274, address274, age274.F03-Rec275, Name275, address275, age275.F03-Rec276, Name276, address276, age276.F03-Rec277, Name277, address277, age277.F03-Rec278, Name278, address278, age278.F03-Rec279, Name279, address279, age279.F03-Rec280, Name280, address280, age280.F03-Rec281, Name281, address281, age281.F03-Rec282, Name282, address282, age282.F03-Rec283, Name283, address283, age283.F03-Rec284, Name284, address284, age284.F03-Rec285, Name285, address285, age285.F03-Rec286, Name286, address286, age286.F03-Rec287, Name287, address287, age287.F03-Rec288, Name288, address288, age288.F03-Rec289, Name289, address289, age289.F03-Rec290, Name290, address290, age290.F03-Rec291, Name291, address291, age291.F03-Rec292, Name292, address292, age292.F03-Rec293, Name293, address293, age293.F03-Rec294, Name294, address294, age294.F03-Rec295, Name295, address295, age295.F03-Rec296, Name296, address296, age296.F03-Rec297, Name297, address297, age297.F03-Rec298, Name298, address298, age298.F03-Rec299, Name299, address299, age299.F03-Rec300, Name300, address300, age300.");

        bufferPool.GET(450);
        Frame frame4 = bufferPool.getBuffers()[0];
        Assert.assertTrue(!frame4.getDirty());
        Assert.assertTrue(!frame4.getPinned());
        Assert.assertTrue(frame4.getBlockId() == 5);
        Assert.assertEquals(new String(frame4.getContent(), StandardCharsets.US_ASCII), "F05-Rec401, Name401, address401, age401.F05-Rec402, Name402, address402, age402.F05-Rec403, Name403, address403, age403.F05-Rec404, Name404, address404, age404.F05-Rec405, Name405, address405, age405.F05-Rec406, Name406, address406, age406.F05-Rec407, Name407, address407, age407.F05-Rec408, Name408, address408, age408.F05-Rec409, Name409, address409, age409.F05-Rec410, Name410, address410, age410.F05-Rec411, Name411, address411, age411.F05-Rec412, Name412, address412, age412.F05-Rec413, Name413, address413, age413.F05-Rec414, Name414, address414, age414.F05-Rec415, Name415, address415, age415.F05-Rec416, Name416, address416, age416.F05-Rec417, Name417, address417, age417.F05-Rec418, Name418, address418, age418.F05-Rec419, Name419, address419, age419.F05-Rec420, Name420, address420, age420.F05-Rec421, Name421, address421, age421.F05-Rec422, Name422, address422, age422.F05-Rec423, Name423, address423, age423.F05-Rec424, Name424, address424, age424.F05-Rec425, Name425, address425, age425.F05-Rec426, Name426, address426, age426.F05-Rec427, Name427, address427, age427.F05-Rec428, Name428, address428, age428.F05-Rec429, Name429, address429, age429.F05-Rec430, Name430, address430, age430.F05-Rec431, Name431, address431, age431.F05-Rec432, Name432, address432, age432.F05-Rec433, Name433, address433, age433.F05-Rec434, Name434, address434, age434.F05-Rec435, Name435, address435, age435.F05-Rec436, Name436, address436, age436.F05-Rec437, Name437, address437, age437.F05-Rec438, Name438, address438, age438.F05-Rec439, Name439, address439, age439.F05-Rec440, Name440, address440, age440.F05-Rec441, Name441, address441, age441.F05-Rec442, Name442, address442, age442.F05-Rec443, Name443, address443, age443.F05-Rec444, Name444, address444, age444.F05-Rec445, Name445, address445, age445.F05-Rec446, Name446, address446, age446.F05-Rec447, Name447, address447, age447.F05-Rec448, Name448, address448, age448.F05-Rec449, Name449, address449, age449.F05-Rec450, Name450, address450, age450.F05-Rec451, Name451, address451, age451.F05-Rec452, Name452, address452, age452.F05-Rec453, Name453, address453, age453.F05-Rec454, Name454, address454, age454.F05-Rec455, Name455, address455, age455.F05-Rec456, Name456, address456, age456.F05-Rec457, Name457, address457, age457.F05-Rec458, Name458, address458, age458.F05-Rec459, Name459, address459, age459.F05-Rec460, Name460, address460, age460.F05-Rec461, Name461, address461, age461.F05-Rec462, Name462, address462, age462.F05-Rec463, Name463, address463, age463.F05-Rec464, Name464, address464, age464.F05-Rec465, Name465, address465, age465.F05-Rec466, Name466, address466, age466.F05-Rec467, Name467, address467, age467.F05-Rec468, Name468, address468, age468.F05-Rec469, Name469, address469, age469.F05-Rec470, Name470, address470, age470.F05-Rec471, Name471, address471, age471.F05-Rec472, Name472, address472, age472.F05-Rec473, Name473, address473, age473.F05-Rec474, Name474, address474, age474.F05-Rec475, Name475, address475, age475.F05-Rec476, Name476, address476, age476.F05-Rec477, Name477, address477, age477.F05-Rec478, Name478, address478, age478.F05-Rec479, Name479, address479, age479.F05-Rec480, Name480, address480, age480.F05-Rec481, Name481, address481, age481.F05-Rec482, Name482, address482, age482.F05-Rec483, Name483, address483, age483.F05-Rec484, Name484, address484, age484.F05-Rec485, Name485, address485, age485.F05-Rec486, Name486, address486, age486.F05-Rec487, Name487, address487, age487.F05-Rec488, Name488, address488, age488.F05-Rec489, Name489, address489, age489.F05-Rec490, Name490, address490, age490.F05-Rec491, Name491, address491, age491.F05-Rec492, Name492, address492, age492.F05-Rec493, Name493, address493, age493.F05-Rec494, Name494, address494, age494.F05-Rec495, Name495, address495, age495.F05-Rec496, Name496, address496, age496.F05-Rec497, Name497, address497, age497.F05-Rec498, Name498, address498, age498.F05-Rec499, Name499, address499, age499.F05-Rec500, Name500, address500, age500.");

        frame4.setPinned(true);
        frame2.setPinned(true);
        bufferPool.GET(540);

        Frame frame5 = bufferPool.getBuffers()[2];
        Assert.assertTrue(!frame5.getDirty());
        Assert.assertTrue(!frame5.getPinned());
        Assert.assertTrue(frame5.getBlockId() == 6);

/*
        console.reset();

        frame5.setPinned(true);
        bufferPool.GET(100);

        Assert.assertEquals(console.toString(), "The corresponding block# 1 cannot be accessed from disk because the memory buffers are full\n\n");
*/

    }

    @org.junit.jupiter.api.Test
    void SET() {
    }

    @org.junit.jupiter.api.Test
    void PIN() {
        BufferPool bufferPool = new BufferPool(1);
        Frame[] buffers = bufferPool.getBuffers();
        buffers[0].setPinned(false);
        buffers[0].setBlockId(1);
        bufferPool.setBuffers(buffers);

        bufferPool.PIN(1);
        buffers = bufferPool.getBuffers();
        Assert.assertTrue(buffers[0].getPinned());

        bufferPool.PIN(2);
        buffers = bufferPool.getBuffers();
        Assert.assertEquals(buffers[0].getBlockId(), 1);
    }

    @org.junit.jupiter.api.Test
    void UNPIN() {
        BufferPool bufferPool = new BufferPool(1);
        Frame[] buffers = bufferPool.getBuffers();
        buffers[0].setPinned(true);
        buffers[0].setBlockId(1);
        bufferPool.setBuffers(buffers);

        bufferPool.UNPIN(1);
        buffers = bufferPool.getBuffers();
        Assert.assertTrue(!buffers[0].getPinned());
    }

    @org.junit.jupiter.api.Test
    void isInPool() {
        BufferPool bufferPool = new BufferPool(3);
        Frame[] bufferFrames = bufferPool.getBuffers();
        bufferFrames[0].setBlockId(1);
        bufferFrames[1].setBlockId(2);
        bufferFrames[2].setBlockId(3);
        bufferPool.setBuffers(bufferFrames);
        Assert.assertEquals(bufferPool.isInPool(4), -1);
        Assert.assertEquals(bufferPool.isInPool(1), 0);
    }

    @org.junit.jupiter.api.Test
    void returnBlockContent() {
    }

    @org.junit.jupiter.api.Test
    void fetchBlock() {
    }

    @org.junit.jupiter.api.Test
    void findEmptyFrame() {
        BufferPool bufferPool = new BufferPool(3);
        Frame[] bufferFrames = bufferPool.getBuffers();
        bufferFrames[0].setBlockId(1);
        bufferFrames[1].setBlockId(2);
        bufferPool.setBuffers(bufferFrames);

        Assert.assertEquals(bufferPool.findEmptyFrame(), 2);

        bufferFrames[2].setBlockId(3);
        bufferPool.setBuffers(bufferFrames);

        Assert.assertEquals(bufferPool.findEmptyFrame(), -1);
    }

    @org.junit.jupiter.api.Test
    void evictFrame() {
        BufferPool bufferPool = new BufferPool(3);
        Frame[] bufferFrames = bufferPool.getBuffers();
        bufferFrames[0].setBlockId(1);
        bufferFrames[1].setBlockId(2);
        bufferFrames[1].setPinned(false);
        bufferFrames[1].setDirty(false);
        bufferFrames[2].setBlockId(3);
        bufferPool.setBuffers(bufferFrames);
        bufferPool.setLastEvictedFrameNumber(0);

        Assert.assertEquals(bufferPool.evictFrame(), 1);

        bufferFrames[1].setPinned(true);
        bufferFrames[2].setPinned(false);
        bufferFrames[2].setDirty(false);
        Assert.assertEquals(bufferPool.evictFrame(), 2);

        bufferFrames[2].setPinned(true);
        Assert.assertEquals(bufferPool.evictFrame(), 0);
    }
}