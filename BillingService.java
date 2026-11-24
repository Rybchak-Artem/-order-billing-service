public class BillingService {

    public static class CustomerProfile {
        public int userId;
        public String email;
        public String passwordHash;
        public String city;
        public int loyaltyPoints;

        public CustomerProfile(int userId, String email, String passwordHash, String city, int loyaltyPoints) {
            this.userId = userId;
            this.email = email;
            this.passwordHash = passwordHash;
            this.city = city;
            this.loyaltyPoints = loyaltyPoints;
        }
    }

    // Stamp Coupling усунено: приймаються лише примітивні параметри
    public static double calculateDeliveryCost(String city, double weightKg) {
        double baseRate = "Ivano-Frankivsk".equals(city) ? 50.0 : 110.0;
        return ("Kyiv".equals(city) ? 80.0 : baseRate) + (weightKg * 12.0);
        
    }

    // Control Coupling усунено: окремий метод для стандартного розрахунку.
    // Common Coupling усунено: ставка податку передається явно.
    public static String processStandardInvoice(double amount, String city, double weightKg, double taxRate) {
        double delivery = calculateDeliveryCost(city, weightKg);
        double total = (amount * (1 + taxRate)) + delivery;
        return "PROCESSED: " + Math.round(total * 100.0) / 100.0;
    }

    // Перевантаження: ставка за замовчуванням 0.20
    public static String processStandardInvoice(double amount, String city, double weightKg) {
        return processStandardInvoice(amount, city, weightKg, 0.20);
    }

    // Control Coupling усунено: окремий метод для розрахунку без податку
    public static String processTaxExemptInvoice(double amount, String city, double weightKg) {
        double total = amount + calculateDeliveryCost(city, weightKg);
        return "TAX_EXEMPT: " + Math.round(total * 100.0) / 100.0;
    }
}
