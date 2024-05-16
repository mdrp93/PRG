public class Pokemon {
    private int id;
    private String name;
    private String type;
    private int attack;
    private int defense;
    private int health;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private String ability;
    private boolean captured;

    public Pokemon(int id, String name, String type, int attack, int defense, int health, int specialAttack, int specialDefense, int speed, String ability, boolean captured) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.ability = ability;
        this.captured = captured;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public String getAbility() {
        return ability;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    @Override
    public String toString() {
        String capturedStatus = isCaptured() ? "Capturado" : "No capturado";
        return "ID: " + id +
                ", Nombre: " + name +
                ", Tipo: " + type +
                ", Ataque: " + attack +
                ", Defensa: " + defense +
                ", Vida: " + health +
                ", Ataque Especial: " + specialAttack +
                ", Defensa Especial: " + specialDefense +
                ", Velocidad: " + speed +
                ", Habilidad: " + ability +
                ", Estado: " + capturedStatus;
    }

}
