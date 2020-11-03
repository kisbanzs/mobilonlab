package hu.bme.aut.vizivandor.ui.tura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hu.bme.aut.vizivandor.R;


public class ListTuraActivity extends AppCompatActivity {

    public static final String KEY_TURA_TYPE = "KEY_TURA_TYPE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tura_turautvonal_informaciok);

        Intent intent = getIntent();
        final int turaType = intent.getIntExtra(KEY_TURA_TYPE, -1);


        TextView tvTuraType = findViewById(R.id.tura_neve);
        tvTuraType.setText(getTuraNeve(turaType));

        View test1View = findViewById(R.id.tura_leirasa);
        TextView turaLeirasanakCime = (TextView) test1View.findViewById(R.id.lenyilo_menu_cime);
        turaLeirasanakCime.setText("A Túra");
        TextView turaLeirasa = (TextView) test1View.findViewById(R.id.lenyilo_menu_tartalma);
        turaLeirasa.setText(getTuraLeirasa(turaType));

        View test2View = findViewById(R.id.reszletezes);
        TextView turaReszletezesenekkCime = (TextView) test2View.findViewById(R.id.lenyilo_menu_cime);
        turaReszletezesenekkCime.setText("Leírás");
        TextView turaReszletek = (TextView) test2View.findViewById(R.id.lenyilo_menu_tartalma);
        turaReszletek.setText(getReszletek(turaType));

        View test3View = findViewById(R.id.megkozelites);
        TextView turaMegkozelitesenekCime = (TextView) test3View.findViewById(R.id.lenyilo_menu_cime);
        turaMegkozelitesenekCime.setText("Megközelítés");
        TextView turaMegkozelites = (TextView) test3View.findViewById(R.id.lenyilo_menu_tartalma);
        turaMegkozelites.setText("Busszal vagy kocsival :D");

        View test4View = findViewById(R.id.egyeb_informaciok);
        TextView turaEgyebCime = (TextView) test4View.findViewById(R.id.lenyilo_menu_cime);
        turaEgyebCime.setText("Egyéb tudnivaló");
        TextView turaEgyeb = (TextView) test4View.findViewById(R.id.lenyilo_menu_tartalma);
        turaEgyeb.setText("Majd lesz itt valami");


}

    private String getTuraNeve(int turaType) {
        switch (turaType) {
            case TuraFragment.TYPE_FELSOTISZA:
                return "A Felsó-Tisza";
            case TuraFragment.TYPE_TOKAJ:
                return "Tokaj és a Bodrogzug";
            case TuraFragment.TYPE_DUNAKANYAR:
                return "Dunakanyar túra";
            case TuraFragment.TYPE_SZIGETKOZ:
                return "A Szigetköz szépségei";
            case TuraFragment.TYPE_ALSODUNA:
                return "Vízivándor tábor az Alsó-Dunán";
            case TuraFragment.TYPE_KOROS:
                return "Vízivándor túra a Körösökön";
            case TuraFragment.TYPE_TISZATO:
                return "Vízivándor tábor a Tisza-tavon";
            default:
                return "NINCS ILYEN TURA";
        }
    }


    private String getTuraLeirasa(int turaNeve) {
        switch (turaNeve) {
            case TuraFragment.TYPE_FELSOTISZA:
                return "A vadregényes Felső-Tiszán szervezett túrán Tiszabecs az első megálló. A megérkezés után, a sátrak elfoglalását követően a csapat délután megismerkedik a túraeszközökkel, amelyeket vízen is kipróbálnak. Napközben vízitúrázás, esténként tábori program várja őket. A Túr folyó természetes fürdőhelyeinek és vadregényes környezetének köszönhetően felejthetetlen élményt nyújt a résztvevőknek.";
            case TuraFragment.TYPE_TOKAJ:
                return "A Bodrogon szervezett tábor székhelye a színvonalas Tokaji Vízitúra Központ. Innen tesznek a táborozók vízi, gyalogos és kerékpáros túrákat. A varázslatos Bodrogzug felfedezése mellett a környék gazdag történelmi látnivalóit is végigjárja.";
            case TuraFragment.TYPE_DUNAKANYAR:
                return "Magyarország egyik legszebb természeti tája a Dunakanyar. Ezt a gyönyörű vidéket fedezi fel Esztergomtól a Római-partig tartó túránk. Minden táborhelyen két napot tölt a csapat így marad idő egyéb programokra is. Egy könnyed gyalogtúra keretében a Rám-szakadékot is bejárhatják a résztvevők. A természeti látnivalók mellet történelmi helyek felkeresésével és városi kirándulásokkal színesíthetjük a programot. A Dunán zajló hajóforgalom miatt a túrát végig motorcsónakok biztosítják.";
            case TuraFragment.TYPE_SZIGETKOZ:
                return "A vadregényes Szigetköz varázslatos folyószakain szervezzük ezt a hangulatos tábort.\n" +
                        "\n" +
                        "A Duna ezer ágra szakadva hálózza be a Kisalföldet, egyedülálló természeti környezetet hozva létre. A Szigetköz számtalan folyómedre, hangulatos szigetvilága értékes ősvadon, amelyet a vízről fedezhetünk fel a legjobban.\n" +
                        "\n" +
                        "A Vízivándor táborok nem titkolt célja, hogy a legifjabb korosztállyal megismertesse, majd az emlékezetes élmények által megszerettesse a vízen töltött szabadidőt. A Dunasziget és Ásványráró könyéki vadvíz-birodalom ideális helyszín arra, hogy örökre megkedveljék a vízitúrázást, szakavatott túravezetők segítségével, minőségi táborhelyeken megszállva.";
            case TuraFragment.TYPE_ALSODUNA:
                return "A Vízivándor táborok egyik legjobb helyszíne a Baja, Kalocsa és Paks környékét bejáró útvonal. Itt nem csak a hatalmas folyót kísérő vadon, a Gemenci-erdő titkait ismerhetik meg a vándortáborozók, de az evezésen kívül tartalmas sportprogramokon is részt vehetnek.";
            case TuraFragment.TYPE_KOROS:
                return "A Körösök vidéke szinte teljesen megőrizte természetközeli állapotát, így igen emlékezetes vadonbéli élményeket szerezhetnek itt a diákok. Ez a túraprogram a Magyar Kerékpáros Turisztikai Szövetséggel karöltve kerül megszervezésre, amely ezáltal kétfajta túranemben járja be a Körösök Völgyét: bringázva, és természetesen a vizek hátán kenuban evezve. A túrakerékpárokat, a túrakenukat a kiegészítő felszereléseket, valamint a helyi túravezetőket a szervezők biztosítják.";
            case TuraFragment.TYPE_TISZATO:
                return "A Tisza-tó egy nagyon különleges hely - bár egy duzzasztóműnek köszönheti létezését, mégis a Természet szépségeinek gazdag tárházát találjuk meg itt. Az UNESCO Világörökség részét is képező vízi birodalom egyedül álló kenutúrák helyszíne.";
            default:
                return "NINCS ILYEN TURA";
        }
    }


    private String getReszletek(int turaType) {
        switch (turaType) {
            case TuraFragment.TYPE_FELSOTISZA:
                return "1-3. nap:\n" +
                        "\n" +
                        "A táborba megérkezés után megismerkedünk a környékkel és a kenuzás alapjaival. A  táborba visszaérve lehetőség van strandröplabdázni, tollasozni, illetve a gyerekek birtokba vehetik a focipályát is, hogy felkészülhessenek az következő napok “megmérettetéseire”.\n" +
                        "\n" +
                        "4. nap:\n" +
                        "\n" +
                        "Reggeli után indulhat a túra Szatmárcsekére. A csomagokat gépjármű szállítja. Érkezés után a helyszínen levezetésképp lehetőség van focizni vagy tollasozni. Akik a kulturális programokat kedvelik, azoknak a közeli hajós fejfás temető és a Kölcsey emlékmű nyújthat érdekes kikapcsolódást. \n" +
                        "\n" +
                        "5.nap:\n" +
                        "\n" +
                        "A ötödik nap reggelén Szatmárcsekén, a vízre szállást követően az úti cél Tivadar. Az egész napos evezés után jól esik majd a pihenés a táborban. \n" +
                        "\n" +
                        "6. nap:\n" +
                        "\n" +
                        "A mai napon a célpont, és egyben az egész túra végállomása Vásárosnamény.  Ha még ez sem lenne elég élmény, lehetősége van a csoportnak , hogy megszervezzék a helyi aquapark meglátogatását fakultatív programként. Egy biztos: ezt nem érdemes kihagyni!\n" +
                        "\n" +
                        "7.nap:\n" +
                        "\n" +
                        "Hazautazás";
            case TuraFragment.TYPE_TOKAJ:
                return "1.nap:\n" +
                        "\n" +
                        "Az első napon megérkezünk Tokaj gazdag történelmű városába és elfoglaljuk szállásunkat a Tokaji Vízitúra Központban. A létesítményhez egy hatalmas füves terület tartozik, ahol a diákok kedvükre focizhatnak, vagy összemérhetik erejüket a szabadtéri kondipályán. A Vízitúra Központ stégjéről indulva már az első nap megismerkedhetnek a kenuzás alapvető technikáival, melyeket a következő napokban használni fognak. Ha a csapat időben érkezik, akkor még a mai, nap, egy rövid bemelegítő túra keretében felkeressük Bodrogzugot, amely a Tisza és a Bodrog folyók összefolyása közti ligeterdős terület.\n" +
                        "\n" +
                        "2.nap:\n" +
                        "A második napon Tokajból indulunk, ahonnan egy egész napos evezéssel a Bodrogzugot fedezik fel VíziVándorok! A védett terület rengeteg természeti csodát tartogat.\n" +
                        "\n" +
                        "3.nap:\n" +
                        "\n" +
                        "Ezen a napon Sátoraljaújhelyre kerékpározunk át, a Bodrog parti településeken át. A bringatúra közben többször megállunk pihenni és a nevezetességeket felfedezni, majd a Sátor-hegység lábánál fekvő történelmi városba érkezünk.\n" +
                        "\n" +
                        "4.nap:\n" +
                        "Ezen a napon két hegyen is túrázunk – a Zemplén kalandpark izgalmas falmászó pályáján próbálhatják ki magukat a fiatalok, majd ezután bejárjuk az erdei túrát ami a kilátóponthoz vezet a hegy csúcsára.\n" +
                        "\n" +
                        "5.nap:\n" +
                        "\n" +
                        "Ezen a napon kerékpárral Felsőberecki  településére túrázunk, ami a következő táborhelyünk. Délután lehetőségünk van a Long erdő természetvédelmi területen evezni és a lassú folyású, gyönyörű vízitúrának számító Bodrog folyón kenuzni. Este tábortűz mellett vacsorázunk.\n" +
                        "\n" +
                        "6.nap:\n" +
                        "\n" +
                        "A Sárospataki vár látogatása szerepel a első helyen a mai programlistán. Érdemes tudni róla, hogy egyike azon kevés magyar váraknak, amely jó állapotában fennmaradt az utókor számára. A Bodrogon evezünk majd több pihenő után érkezünk meg Sárospatakra, ahol a történelmi hagyományok és a múlt érdekességeinek megismerése után a vár felfedezésére indulunk.  A vállalkozó szellemű csapatok akár látogatást tehetnek Magyarország egyik legszebb tengerszeménél (Megyer-hegyi tengerszem) amely a közelben található. A nap végén a célállomás ismét a tokaji szállás, ahová a már megismert útvonalon tekerünk vissza, itt strandröplabda, tollas és foci, majd vacsora és pihenés várja a gyerekeket.\n" +
                        "\n" +
                        "7.nap:\n" +
                        "\n" +
                        "Ezen a napon elbúcsúzunk a Hegyaljától és a Bodrogzugtól és élményekkel gazdagodva hazautazunk.";
            case TuraFragment.TYPE_DUNAKANYAR:
                return "1.nap:\n" +
                        "\n" +
                        "Az első napon megérkezünk Esztergomba, elfoglaljuk a táborhelyünket. Ezt követően a város szélén, egy kellemes Duna szakaszon indulunk az első napi, gyakorló túrára, ahol elsajátíthatjuk az evezés technikáját, megtanulhatjuk a legfontosabb tudnivalókat. A vízre szállás a Duna egyik kis csendes mellékágában történik, ahol nagyon jól lehet gyakorolni a kenuzást még a kezdőknek is. Az első napon a vacsora biztosított egy közeli kisétteremben\n" +
                        "\n" +
                        "2. nap:\n" +
                        "\n" +
                        "Reggeli után vízre szállunk és egy hajóforgalomtól elzárt 7 Km hosszú öbölben evezhetünk. Ez nagyon jó lehetőség, hogy mindenki elsajátítsa a biztonságos víziközlekedést. A reggelinél kapunk élelmiszercsomagot amit vízhatlan hordóban magunkkal vihetünk, ha az idő engedi és a csapat is jólérzi magát akkor vacsoráig nem kell vissza menni a táborba. Van fürdésre alkalmas partszakasz. A tábor területén focizni, kosárlabdázni, tollasozni is van lehetőség. És mindezek mellet Esztergom belvárosa és a bazilika is sétatávolságra van.\n" +
                        "\n" +
                        "3-4. nap:\n" +
                        "\n" +
                        "Ezt a napot is a reggelivel és az útra készített szendvicsek átvételével kezdjük. Utána viszont mindenki összepakolja a holmiját. A tábort rendben hagyjuk ott a következő csapatnak. Mindenki bepakolhatja a holmiját egy kisteherautóba így csak az evezéshez szükséges felszerelést kell magunkkal vinni a kenuba. Vízre szállás után a csapat Dömös felé a Dunafolyásának irányba túrázik. Vízállástól függően vagy egy kis szigeten vagy a Dunaparton ebédelünk. A nap második felére érkezünk meg Dömösre. Itt elfoglaljuk az új táborhelyünket.\n" +
                        "\n" +
                        "Másnap számos program közül választhat a csapat.\n" +
                        "\n" +
                        "-Gyalog túrával elérhető a Pilis három érdekes helyszíne. A Rám-szakadék, a Vadálló-kövek és a Prédikálószék. Ide egy túrázásra alkalmas zárt cipő mindenképpen szükséges.\n" +
                        "\n" +
                        "-A kempingen lévő medencét a ViziVándorok ingyenesen és korlátlanul használhatják. Itt egy úszómester vigyáz a gyerekek biztonságára.\n" +
                        "\n" +
                        "-A kemping előtti füves part kiválóan alkalmas focizni tollasozni.\n" +
                        "\n" +
                        "5-6.nap:\n" +
                        "\n" +
                        "Ismét vízre szállunk és folytatunk az utunkat Szentendrefelé. Ezen a szakaszon a visegrádi várat elhagyva a Szentendrei Duna-ágon evezünk majd. A Szentendrei Papszigeten lévő kempingben van a következő táborhelyünk.\n" +
                        "\n" +
                        "Másnap itt is több programból választhatunk:\n" +
                        "\n" +
                        "-Szentendrei városnézés fagyizással egybekötve. Szentendre hangulata egyedül álló Magyarország városai között – girbe-gurba utcáit, macskaköves tereit felfedezni hatalmas élmény\n" +
                        "\n" +
                        "-Szentendrei skanzen meglátogatása\n" +
                        "\n" +
                        "-Ebben a kempingben is van medence\n" +
                        "\n" +
                        "– túra vezető felügyeletével kilehet próbálni a SUP-ot, ez egy nagyon népszerű és gyorsan fejlődő sport. egy szörfdeszkán állva kell evezni\n" +
                        "\n" +
                        "7. nap:\n" +
                        "\n" +
                        "A Római-parton lesz a túra végállomása, ahol a gyerekek még egy kicsit kiélvezhetik az önfeledt szórakozás utolsó pillanatait, majd rengeteg új élménnyel térnek haza.\n" +
                        "\n" +
                        "Álltalános infók:\n" +
                        "\n" +
                        "A három táborhelyszín mindegyikén 10szemályes katonai sátrak vannak felállítva. mindegyikben 10 kempingágy van és egy lámpa. A tanári vagy vezetői sátorban van áram. itt lehet a telefonokat tölteni.\n" +
                        "\n" +
                        "étkezés: elsőnap csak vacsorát van. utolsónap reggeli és úticsomag, a többi napokon reggeli úticsomag és vacsora. Az úticsomag álltalában 3 db szendvics és víz.";
            case TuraFragment.TYPE_SZIGETKOZ:
                return "1-2. nap:\n" +
                        "\n" +
                        "Dunasziget a túra első megállója, ahol két éjszakát tölt el a csapat. A hely sajátossága egy hegyi patak tisztaságú szivárgó ág, amelyen igazi különleges élmény a kenuzás. A csillagtúrázások között sok szárazföldi program várja az ideérkezőket. Egy interaktív kiállítás keretében, a ViziVándorok megismerhetik a környék élővilágát. A helyszínen tartott ökológiai oktatás keretében vízminta vétellel kielemezhetik annak tisztaságát, összetételét. \n" +
                        "\n" +
                        "3-4. nap:\n" +
                        "\n" +
                        "A reggeli után Kisbodak a következő úti cél, ahol két éjszakát töltenek a vándorok. A sportolni vágyóknak lehetőségük van strandröplabdázni, tollasozni, focizni. Érdekes programként a gátőrház látogatást ajánljuk, ahol a Vízivándorok többet megtudhatnak a vízszabályozásról, és betekintést nyerhetnek a gátőrök mindennapi életébe.\n" +
                        "\n" +
                        "5-6. nap:\n" +
                        "\n" +
                        "Az ötödik nap célja Ásványráró, ahol két éjszakát tölt el a csapat. Itt megannyi program várja a gyerekeket: strandröplabda, tollas, foci, lovaglás, akadálypálya. Akik elég bátrak, és ki merik engedni a hangjukat alkalmuk nyílik megragadni a mikrofont egy karaoke est keretében.\n" +
                        "\n" +
                        "7.nap:\n" +
                        "\n" +
                        "Hazautazás";
            case TuraFragment.TYPE_ALSODUNA:
                return "1.nap:\n" +
                        "\n" +
                        "A Duna alsó szakaszát és a Gemenci erdő értékeit felfedező vízitúra Pakson veszi kezdetét. A szállás elfoglalása után gyakorlás következik a vízen, hogy másnap már mindenki magabiztosan vághasson bele a vízi kalandokba. Itt is lehetőség lesz számos különböző sporttevékenység kipróbálására, gyakorlására (strandröplabda, tollas, stb). Tartalmas programot nyújt a Forráspont Energiaház, amely megismerteti a gyerekekkel a környezettudatos és energia-hatékony életmódot, valamint annak előnyeit példákon és gyakorlati megoldásokon keresztül. Ezen felül városnézés közben sok érdekesség tárul a felfedezők szeme elé. Csodás ez a helyszín, ahol levezetőként strandolási lehetőség is van. A jókedv és a sok kacagás garantált!\n" +
                        "\n" +
                        "2 .nap:\n" +
                        "\n" +
                        "Ezen a napon a Dunán evezünk Kalocsáig, a Meszesi Duna partig. Itt a folyóparton egy nagyon klassz vízitúra táborhelyen lesz a szállásunk, a diákok a parti fövenyen strandolhatnak,  egy vadregényes kis szigetet fedezhetmnek fel, este pedig a tábortűz köré gyűlik a vidám túracsapat.\n" +
                        "\n" +
                        "3-4 nap:\n" +
                        "\n" +
                        "Másnap Gemenc-Pörböly felé veszi a csapat az irányt, ahol két éjszakát töltenek el. A pörbölyi Ökoturisztikai Központ, sokféle alternatív programlehetőséget kínál: lehetőség nyílik az erdő felfedezésére az erdei vasúttal. Továbbá, a központtól csupán néhány percre található a Vadmegfigyelő hely, ahol erdei állatokat pillanthatnak meg azok, akik türelmesen várnak. Visszatérve a szállásra sportolási lehetőség várja a gyerekeket.\n" +
                        "\n" +
                        "5.nap:\n" +
                        "\n" +
                        "Ezen a napon a vízitúra Bajára vezet, itt két éjszakát töltenek el a gyerekek. Egy kiadós evezéssel érjük el a gazdag történelmi hagyományokkal rendelkező várost. A szálláshelyen van lehetőség strand röplabdázni, tollasozni, focizni, és egyéb sportokat kipróbálni.\n" +
                        "\n" +
                        "6.nap:\n" +
                        "Ezen a napon a híres Sugovica csatornán evezünk egy jót, Ha még ez sem lenne elég élmény, lehetősége van a csoportnak , hogy megszervezzék a helyi Kalandpark meglátogatását , amely a felnőttek számára is remek kikapcsolodási – és szórakozási lehetőséget biztosít. A túra végén megrendezésre kerül a Sárkányhajós verseny, ahol a résztvevők összemérhetik erejüket, a legjobbakat pedig oklevéllel jutalmazzuk.\n" +
                        "\n" +
                        "7.nap:\n" +
                        "Ezen a napon egy rövid vízitúra után az összepakolás és a fájdalmas búcsú marad – az Alsó-Duna tábor véget ér, a hazautazás következik";
            case TuraFragment.TYPE_KOROS:
                return "1.-2. nap\n" +
                        "\n" +
                        "A program első napján érkezés Békéscsabán keresztül, a gyönyörű természeti környezetben, (közvetlenül vízparton) található, Békés- Dánfoki Üdülőközpontba Békésre, ahol szállásunk és ellátásunk is lesz az elkövetkező 2 napon. Első este vacsora után tábornyitó tábórtűz a vízparton.\n" +
                        "\n" +
                        "A Békés-dánfoki Üdülőközpontból kiindulva a gyerekek és kisérőik helyi túravezető vezetésével, és csillagtúra formájában bringákkal tekerhetnek a Körösök mentén, felfedezve így a környező vidéket. A túra során útba ejthetik a legérdekesebb látnivalókat, Pl. Békéscsabán a Munkácsy Múzeumot és Emlékházat, a „Kollbászfalut”, a Pósteleki Kastélyt és gyönyörű parkját. Gyulán az Almássy Kastélyt, a Gyulai Várat és Várfürdőt, a Retró Múzeumot, Erkel Ferenc Emlékházat és Múzeumot stb…\n" +
                        "\n" +
                        "3.- 4. – 5.nap\n" +
                        "\n" +
                        "A harmadik naptól a gyerekek és kisérőik túrakenukban evezve folytatják tovább kalandos útjukat lefelé a Kettős, majd a Hármas-Körösön. Innentől már váltakozó szálláshelyeken töltik majd az éjszakákat, hangulatos csapatsátrakban. Táborhelyek: Köröstarcsán, Gyomaendrődön és Szarvason. A napi evezős távok 20-25 km között.\n" +
                        "\n" +
                        "Az igazán vadregényes és szinte még érintetlen Körösökön, menetközben több pihenő pont is beiktatásra kerül, ahol érdekességekkel és egyéb látnivalókkal találkozhatnak az ifjú vízi vándorok. Pl. Bodoky Károly Vízügyi Múzeum, Kereki-híd (Petőfi emlékhely), a Kettős és a Sebes-Körös összefolyása, Félhalmi Horgász és Halásztanya, Peresi- Körösvölgyi Természetvédelmi Bemutatóház stb.\n" +
                        "\n" +
                        "6– 7. nap\n" +
                        "Az utolsó táborhely közvetlenül a Szarvasi Holt Körös partján található Szarvasi Ifjúsági Táborban lesz. Itt is 2 éjszakát töltenek az ifjú vándortáborosok csak úgy, mint előzőleg Békés-Dánfokon. Fő közlekedési eszközünk újra a kerékpár lesz, amelynek segítségével bejárhatjuk a környék nevezetességeit, érdekességeit.\n" +
                        "\n" +
                        "Pl. Szarvasi Arborétum, Anna Liget, Körös-Maros Nemzeti Park bemutató helye, Békésszentandrási Duzzasztómű, Bolza Kastély, a Történelmi Magyarország középpontját jelképező (szélmalom) emlékhely, Szarvasi Vízi Színház stb… Utolsó este vacsora után táborzáró program a tűz mellett.\n" +
                        "\n" +
                        "Utolsó (7.) napon, reggeli után csomagolás és hazautazás Szarvasról.";
            case TuraFragment.TYPE_TISZATO:
                return "1.nap\n" +
                        "A csoportok a megérkezés után elfoglalják a szálláshelyüket, igény szerint megnyújtóztatják a hosszú úttól megfáradt tagjaikat. Ki-ki kedvére mozoghat, megismerheti a helyszínt, ahol a következő napokat tölti majd. Ha már mindenki otthonosan berendezkedett, jöhet az első megmérettetés, a „vízhez szoktatás”. Kíváncsiak vagyunk, kinek, mit jelent az evezés (élményt e vagy megpróbáltatást) és mit lehet majd kihozni a csoportból. Megtanítjuk a legénységet az evezés és kormányozás fortélyaira, elsajátítjuk hogyan szálljunk ki és be a hajóba a vízen. Ezt követően evezünk a közelben egy bemelegítő kört. Miután visszaértünk, vár minket a vacsora, hogy jóllakottan készülhessünk a következő nap élményeire.\n" +
                        "\n" +
                        "2.nap\n" +
                        "Ha túljutunk a nem túl korai ébredezésen, vár ránk egy kiadós reggeli, hogy bírjuk szusszal a napot. A környék felfedezésére indulunk, természetesen vízen. Minden titkos zugba benézünk, végigportyázunk az eldugott csatornákon, miközben számos információt gyűjthetünk vezetőinktől a Tisza-tóról. Ha megéhezünk, megállunk egy nyugalmas helyen és elfogyasztjuk a magunkkal hozott ellátmányt, majd akinek kedve van akár csobbanhat is egyet a vízben. Ez utóbbit napközben többször is megejtjük majd. A távolságot és az útvonalat a csoport teherbírásához igazítjuk.\n" +
                        "\n" +
                        "3.nap\n" +
                        "A Tiszafüreden kezdő csoport ezen a napon lazíthat kicsit: reggeli után irány a strand! Természetesen a vízi vándor hajóval közlekedik, ezért az evezés a mai nap sem marad el! A szórakozásunknak csak az éhségünk szab majd határt: ha mát elfogyott az összes ellátmányunk, irány a tábor és a vacsora!\n" +
                        "\n" +
                        "A Poroszlón kezdő csoport ezen a napon kicsit más szemszögből ismeri meg a Tavat: reggeli után két kerékre pattan, és az autós közlekedéstől elzárt töltésen és kerékpáros úton szeli a kilométereket. Ha már eleget láttunk és tekertünk, akkor vár minket a vacsora.\n" +
                        "\n" +
                        "4.nap\n" +
                        "Ezen a napon mindkét csoport korán kel majd. A reggelizésre egy kis pakolás fog ráhangolni. Mindenki összeszedi a heti holmiját, hogy a szállítóautó el tudja vinni az új helyszínre (Tiszafüred- Poroszló, Poroszló- Tiszafüred). Ha a csoportok jól bírják a tempót, belefér az időnkbe egy kis strandolás is menet közben. Ezen a napon a két csoport bázist cserél – Poroszlóról Tiszafüredre indulnak a Vízivándorok, a felszerelésüket vízálló zsákokban szállítva eveznek át az új szállásra – szemben velük a tiszafürediek indulnak Poroszlóra. A délután az új táborhely berendezésével, belakásával telik.Az új helyszínre érve mindenki berendezkedik, és aznapra már csak a vacsora marad.\n" +
                        "\n" +
                        "5.nap\n" +
                        "A Poroszlón kezdő csoport ezen a napon lazíthat kicsit: reggeli után irány a strand! Természetesen a vízi vándor hajóval közlekedik, ezért az evezés a mai nap sem marad el! A szórakozásunknak csak az éhségünk szab majd határt: ha mát elfogyott az összes ellátmányunk, irány a tábor és a vacsora!\n" +
                        "\n" +
                        "A Tiszafüreden kezdő csoport ezen a napon kicsit más szemszögből ismeri meg a Tavat: reggeli után két kerékre pattan, és az autós közlekedéstől elzárt töltésen és kerékpáros úton szeli a kilométereket. Ha már eleget láttunk és tekertünk, akkor vár minket a vacsora.\n" +
                        "\n" +
                        "6.nap:\n" +
                        "Az utolsó előtti napon megpróbálunk minél több élményt magunkba szedni. Ehhez viszont erőre és kitartásra lesz szükség. A távolabbi célpontokat ostromoljuk, ahová csak az igazán kitartóak jutnak el. Vízre hát, várnak minket a Tisza-tó leginkább védett és elrejtett zugai.\n" +
                        "\n" +
                        "7.nap: A sátrak rendbetétele után búcsút intünk a Tisza-tónak, a tábornak és egymásnak is. De senki ne csüggedjen, bármikor visszatérhet, hiszen jövőre is vár a Vízi Vándor élet!";
            default:
                return "NINCS ILYEN TURA";
        }
    }


}