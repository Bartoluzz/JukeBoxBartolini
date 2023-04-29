package com.example.jukeboxbartolini;

public class Canzone {

    String nome;
    String link;
    int path;
    int img;
    
    String testo;

    static int numCanzoni = 10;

    public Canzone(String nome, String link, int path, int img, String testo){
        this.nome = nome;
        this.link = link;
        this.path = path;
        this.img = img;
        this.testo = testo;
    }

    public static Canzone[] init(){
        Canzone c[] = new Canzone[numCanzoni];

        c[0] = new Canzone("Bottle Girls", "https://youtu.be/4Dd1nmjiRko", R.raw.bottlegirlsmusic,R.raw.bottlegirls, "bottlegirlstesto.txt");
        c[1] = new Canzone("Charlie be Quite", "https://youtu.be/vo_XZmKlJ3A", R.raw.charliebequitemusic , R.raw.charliebequite,"charliebequitetesto.txt");
        c[2] = new Canzone("Come & Go", "https://youtu.be/5Di20x6vVVU" , R.raw.comeandgomusic , R.raw.comeandgo,"comeandgotesto.txt");
        c[3] = new Canzone("Grown Man", "https://youtu.be/KWyvYbfsWMA" , R.raw.grownmanmusic , R.raw.grownman,"grownmantesto.txt");
        c[4] = new Canzone("High", "https://youtu.be/OfjWhEV9NpE", R.raw.highmusic , R.raw.high,"hightesto.txt");
        c[5] = new Canzone("Motto", "https://youtu.be/0YKOxtOb44c", R.raw.mottomusic , R.raw.motto,"mottotesto.txt");
        c[6] = new Canzone("Never Sleep", "https://youtu.be/BnsWBJnyJiQ", R.raw.neversleepmusic , R.raw.neversleep,"neversleeptesto.txt");
        c[7] = new Canzone("SideWalk", "https://youtu.be/Okd7LqeewrA", R.raw.sidewalksmusic , R.raw.sidewalk,"sidewalktesto.txt");
        c[8] = new Canzone("Thausend Bad Times" ,"https://youtu.be/pENmTv53Scg" ,R.raw.thausendbattimesmusic ,R.raw.thausendbadtimes,"thausendbadtimestesto.txt");
        c[9] = new Canzone("We Paid", "https://youtu.be/2wowASwbq-w" ,R.raw.wepaidmusic , R.raw.wepaid,"wepaidtesto.txt");

        return c;
    }


}
