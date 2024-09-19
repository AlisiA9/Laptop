public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
 
        laptops.add(new Laptop(8, 512, "Windows", "Black"));
        laptops.add(new Laptop(16, 256, "Linux", "Silver"));
        laptops.add(new Laptop(4, 1024, "Windows", "White"));
        laptops.add(new Laptop(32, 2048, "MacOS", "Gray"));
    }

    public void filterLaptops() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> criteria = new HashMap<>();

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ (RAM)");
            System.out.println("2 - Объем ЖД (HDD)");
            System.out.println("3 - Операционная система (OS)");
            System.out.println("4 - Цвет (Color)");
            System.out.println("5 - Завершить ввод критериев");

            int choice = scanner.nextInt();
            if (choice == 5) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Введите минимальное значение ОЗУ в ГБ: ");
                    int ramValue = scanner.nextInt();
                    criteria.put("ram", ramValue);
                    break;
                case 2:
                    System.out.print("Введите минимальный объем ЖД в ГБ: ");
                    int hddValue = scanner.nextInt();
                    criteria.put("hdd", hddValue);
                    break;
                case 3:
                    System.out.print("Введите операционную систему: ");
                    String osValue = scanner.next();
                    criteria.put("os", osValue);
                    break;
                case 4:
                    System.out.print("Введите цвет: ");
                    String colorValue = scanner.next();
                    criteria.put("color", colorValue);
                    break;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }

            System.out.print("Хотите добавить еще один критерий? (да/нет): ");
            String continueInput = scanner.next();
            if (!continueInput.equalsIgnoreCase("да")) {
                break;
            }
        }


        Set<Laptop> filteredLaptops = new HashSet<>(laptops);
        for (Laptop laptop : laptops) {
            boolean matches = true;

            if (criteria.containsKey("ram") && laptop.getRam() < (int) criteria.get("ram")) {
                matches = false;
            }
            if (criteria.containsKey("hdd") && laptop.getHdd() < (int) criteria.get("hdd")) {
                matches = false;
            }
            if (criteria.containsKey("os") && !laptop.getOs().equalsIgnoreCase((String) criteria.get("os"))) {
                matches = false;
            }
            if (criteria.containsKey("color") && !laptop.getColor().equalsIgnoreCase((String) criteria.get("color"))) {
                matches = false;
            }

            if (!matches) {
                filteredLaptops.remove(laptop);
            }
        }


        System.out.println("Ноутбуки, соответствующие критериям:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    public static void main(String[] args) {
        LaptopStore store = new LaptopStore();
        store.filterLaptops();
    }
}  