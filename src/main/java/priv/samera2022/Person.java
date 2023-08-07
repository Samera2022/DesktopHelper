package priv.samera2022;

public class Person {
    private String name;
    public Person(String name){this.name=name;}
    public void eat(){};
    public void drink(){};
    public void playGAME(){};
}
class Persons{
        public static Person toame = new Person("toame"){
            @Override
            public void playGAME(){
                System.out.println("DO U LIKE WHAT U SEE?");
            };
        };

    public static void main(String[] args) {
        Persons.toame.playGAME();
    }
}
