package hu.bme.aut.vizivandor.ui.turainditas;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Pozition {

    public LatLng pozition(String s){

        //String szo = s;
        //if(){


        ArrayList<LatLng> back = new ArrayList<LatLng>();

            switch(s){
                case "Hungary":
                    return new LatLng(47.460886, 19.051869);
                case "Tura1":
                    return new LatLng(48.104927,22.829234);
                       /* back.add(new LatLng(48.104927,22.829234));
                        back.add(new LatLng(48.099701,22.624464));
                        back.add(new LatLng(48.061861,22.519040));
                        back.add(new LatLng(48.127612,22.340048));
                        for(int i=0; i<back.size(); i++){
                            return back.get(i);
                        }*/

                case "Szatmarcseke":
                    return new LatLng(48.099701,22.624464);
                case "Tivadar":
                    return new LatLng(48.061861,22.519040);
                case "Vasarosnameny":
                    return new LatLng(48.127612,22.340048);

                case "Tura2":
                    return new LatLng(48.137781,21.400946);
                case "Bodrogzug":
                    return new LatLng(48.167749,21.398657);
                case "Satoraljaujhely":
                    return new LatLng(48.413187,21.637227);
                case "Felsoberecki":
                    return new LatLng( 48.360649,21.693310);
                case "Sarospatak":
                    return new LatLng( 48.315088,21.568245);

                case "Tura3":
                    return new LatLng( 47.789443,18.731162);
                case "Tura4":
                    return new LatLng( 47.940391,17.357821);
                case "Tura5":
                    return new LatLng( 46.592602,18.860733);
                case "Tura6":
                    return new LatLng( 46.759521,21.153568);
                case "Tura7":
                    return new LatLng( 47.645895,20.660313);


                default:
                    System.out.println("nincs ilyen megjelenitheto terkep");
                    break;
            }
        //}

        return null;
    }
}
