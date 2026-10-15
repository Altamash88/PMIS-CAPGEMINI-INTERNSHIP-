class Battery{
    int capacityMAH;
    Battery(int capacityMAH){
        this.capacityMAH=capacityMAH;

    }
    void drain(){
        System.out.println("Battery Capacity is "+ (100-capacityMAH) +" Mah");
    }
}

class Camera{
    int pix;
    Camera(int pix){
        this.pix=pix;
    }
    void takePhoto(){
        System.out.println("Click.... Photo Captured with : "+pix +"Mp Sensor ");
    }
}

class Phone{
    String brand;
    Battery battery;
    Camera camera;

    Phone(String brand , Battery battery ,Camera camera){
        this.brand=brand;
        this.battery = battery;
        this.camera = camera;
    }
    void snapPicture(){
        System.out.println("[ "+ brand +" ]"+ " is active.");
        camera.takePhoto();
        battery.drain();
    }
}
public class IsHasRelationShip3 {
    public static void main(String[] args) {
        Battery bat = new Battery(87);
        Camera cam = new Camera(48);
        Phone smtphone = new Phone("Oppo", bat, cam);
        smtphone.snapPicture();
    }
}
