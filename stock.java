import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance = 10000; // Starting balance

    void buyStock(String stockName, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Not enough balance!");
            return;
        }
        holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
        balance -= cost;
        System.out.println("Bought " + quantity + " of " + stockName);
    }

    void sellStock(String stockName, int quantity, double price) {
        if (!holdings.containsKey(stockName) || holdings.get(stockName) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(stockName, holdings.get(stockName) - quantity);
        balance += quantity * price;
        System.out.println("Sold " + quantity + " of " + stockName);
    }

    void showPortfolio() {
        System.out.println("\nPortfolio:");
        for (String stock : holdings.keySet()) {
            System.out.println(stock + ": " + holdings.get(stock) + " shares");
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();
        Map<String, Stock> market = new HashMap<>();
        
        // Adding some stocks to the market
        market.put("AAPL", new Stock("AAPL", 150));
        market.put("GOOGL", new Stock("GOOGL", 2800));
        market.put("TSLA", new Stock("TSLA", 700));

        while (true) {
            System.out.println("\n1. View Market\n2. Buy Stock\n3. Sell Stock\n4. View Portfolio\n5. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nMarket Prices:");
                    for (Stock stock : market.values()) {
                        System.out.println(stock.name + ": $" + stock.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buyStock = scanner.next().toUpperCase();
                    if (!market.containsKey(buyStock)) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();
                    portfolio.buyStock(buyStock, buyQty, market.get(buyStock).price);
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellStock = scanner.next().toUpperCase();
                    if (!market.containsKey(sellStock)) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();
                    portfolio.sellStock(sellStock, sellQty, market.get(sellStock).price);
                    break;

                case 4:
                    portfolio.showPortfolio();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
