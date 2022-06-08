# Food Vending Machine
It's time for food :hamburger:
## Foreword
This is a small OOP puzzle quiz about Solid Programming Principle. Developer will have to apply OOP principles to solve the problem.
## Business Specification
Adnovum company is about to set up a food vending machine, since many employees are hungry.
The **Food Vending Machine** requirement is fairly simple:
- It contains `Food` (of course)
- People can add **balance** , in this scenario, are different type of `Banknote` to the machine
- Since Adnovum is an international company, the machine then will be used by
people from different countries. The machine must be able to consume different type
of `Currency`
- The machine will serve international food, which mean each food may have different
currency price tag
- Once the machine balance currency is set, it must be smart enough to *convert currency to the machine based currency* for processing balance.

#### Use case Diagram
![Use case diagram](https://i.imgur.com/BFZPmOB.png)
## Technical Specification
Breaking down the business specs, we have the following classes to implements:
- `Food` :
A food will has name and `PriceTag`
![Food Class Diagram](https://i.imgur.com/jGl3XbS.png)

- `Banknote` and `PriceTag` have `Currency` and base values.
Our machine starts with some bases currencies VND, CHF, USD and EUR, come with a set
of constants as conversion rate.
![Money Class Diagram](https://i.imgur.com/Q1SG4AI.png)

- `FoodVendingMachine` has `addBalance` method which consume `Banknote`, `addFood` to fill up the machine

![Food Vending Machine Class Diagram](https://i.imgur.com/SOxOmVG.png)
### The All-knowing Food Vending Machine :slot_machine: :ice_cream:  :beer:
Adnovum company hired a monkey coder to implement the machine sets, the hardest part is the currency conversion handle, and our monkey coder had no problem to handle it "nicely"
```
@Override
    public void addBalance(Banknote note) {

        /* Our machine currently use VND, so let's check what you put in */
        if (Currency.VND.equals(getCurrency())) {
            if (Currency.VND.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.VND_TO_VND;
            }
            if (Currency.CHF.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.CHF_TO_VND;
            }
            if (Currency.USD.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.USD_TO_VND;
            }
            if (Currency.EUR.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.EUR_TO_VND;
            }
        }

        if (Currency.USD.equals(getCurrency())) {
            if (Currency.USD.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.USD_TO_USD;
            }
            if (Currency.CHF.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.CHF_TO_USD;
            }
            if (Currency.VND.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.VND_TO_USD;
            }
            if (Currency.EUR.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.EUR_TO_USD;
            }
        }

        if (Currency.CHF.equals(getCurrency())) {
            if (Currency.CHF.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.CHF_TO_CHF;
            }
            if (Currency.VND.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.VND_TO_CHF;
            }
            if (Currency.USD.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.USD_TO_CHF;
            }
            if (Currency.EUR.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.EUR_TO_CHF;
            }
        }

        if (Currency.EUR.equals(getCurrency())) {
            if (Currency.EUR.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.EUR_TO_EUR;
            }
            if (Currency.CHF.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.CHF_TO_EUR;
            }
            if (Currency.USD.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.USD_TO_EUR;
            }
            if (Currency.VND.equals(note.getCurrency())) {
                this.balance += note.getAmount() * CurrencyExchangeRateConstants.VND_TO_EUR;
            }
        }
    }
```

> The Vending Machine is so powerful that beside giving out food, it even know how to convert different type of currencies. May be someday in the future, it may even offer you to change some CHF banknote into VND for free, even you if don't buy the food. How generous is that!

## What's going wrong ?
The monkey coder might not be aware that stuffing too many unrelated functionality to the machine might lead to a bigger problem in the future. What if the company offer service to exchange CHF into VND banknotes for Swiss employee who comes to Vietnam, is it suitable if we tell him to reach the vending machine to get what he wanted ? And what if another type of currency, namely _Hungarian Forint_ is added, we might have to dive into the machine implementation and add modification as well.

Seeing the horrible over-generous vending machine, Adnovum company design to hire some **Solid Programmers** teams to have the problem fixed up.

### What should the Solid Programmers do :construction_worker:
- Can you untie the knot inside the vending machine ?
- Make sure yours test cases cover all most all the code. Luckily the monkey coder
already write some test cases, so you can quickly refer and make a good use from them

###### From The Green Rubber Ducks :hatching_chick: Team with :heart:
---
# The Green Duck Food Vending Machine

## What is the problem ?
The Monkey Food Vending Machine knows too many thing beside its responsibility. Currency Converter ability is not its job, so we create a class solely for currency conversion.
Of course we can split the money conversion implementation in `MonkeyFoodVendingMachine`, but is it a good way to do ?
Let's take a look at the current money conversion implementation
```
/* Our machine currently use VND, so let's check what you put in */
if (Currency.VND.equals(getCurrency())) {
    if (Currency.VND.equals(note.getCurrency())) {
        this.balance += note.getAmount() * CurrencyExchangeRateConstants.VND_TO_VND;
    }
    if (Currency.CHF.equals(note.getCurrency())) {
        this.balance += note.getAmount() * CurrencyExchangeRateConstants.CHF_TO_VND;
    }
    if (Currency.USD.equals(note.getCurrency())) {
        this.balance += note.getAmount() * CurrencyExchangeRateConstants.USD_TO_VND;
    }
    if (Currency.EUR.equals(note.getCurrency())) {
        this.balance += note.getAmount() * CurrencyExchangeRateConstants.EUR_TO_VND;
    }
}
```

`CurrencyExchangeRateConstants.java`
```
public class CurrencyExchangeRateConstants {

    /* To USD Exchange Rate */
    public static final double CHF_TO_USD = 1.01555;
    public static final double EUR_TO_USD = 1.17696;
    public static final double VND_TO_USD = 0.0000438964;
    public static final double USD_TO_USD = 1;

    /* To VND Exchange Rate */
    public static final double CHF_TO_VND = 23072.81;
    public static final double EUR_TO_VND = 26738.82;
    public static final double VND_TO_VND = 1;
    public static final double USD_TO_VND = 1 / VND_TO_USD;

    /* To EUR Exchange Rate */
    public static final double CHF_TO_EUR = 0.862537;
    public static final double EUR_TO_EUR = 1;
    public static final double VND_TO_EUR = 1 / EUR_TO_VND;
    public static final double USD_TO_EUR = 1 / EUR_TO_USD;

    /* To CHF Exchange Rate */
    public static final double CHF_TO_CHF = 1;
    public static final double EUR_TO_CHF = 1 / CHF_TO_EUR;
    public static final double VND_TO_CHF = 1 / CHF_TO_VND;
    public static final double USD_TO_CHF = 1 / CHF_TO_USD;

    private CurrencyExchangeRateConstants() {}
}
```
Currency exchange rate is very likely to change in the future, namely if the exchange rate of **VND** goes up,
`CurrencyExchangeRateConstants` will have to update up to three conversion rates: toUSD, toCHF, toEUR.
And back at the question at the beginning, what if we add new currency type, namely _Hungarian Forint_ is added, a lot of constants variables
will be introduced.
Why don't we shorten things up, by using a most common type of currency as an intermediary. Since USD is considered as the most widely used currency in the world,
if we want to convert **VND** to **CHF**, we simply do like this
> **VND** -> **USD** -> **CHF**

And we only need to store 2 conversion rate, instead of 3 like the current one.
If _Hungarian Forint_ (HUF) is added, we'll just have to add only one new conversion rate **HUF_TO_USD**

`CurrencyConverter.java`
```
public class CurrencyConverter {
    private CurrencyConverter() {}

    public static double exchangeCurrency(Currency from, Currency to, double amount) {
        if (Currency.USD.equals(to)) {
            return convertToUSDCurrency(from, amount);
        }
        if (Currency.USD.equals(from)) {
            return 1 / convertToUSDCurrency(to, 1/amount);
        }
        return exchangeCurrency(Currency.USD, to, exchangeCurrency(from, Currency.USD, amount));
    }

    private static double convertToUSDCurrency(Currency currency, double amount) {
        if (Currency.VND.equals(currency)) {
            return amount * CurrencyExchangeRateConstants.VND_TO_USD;
        }
        if (Currency.EUR.equals(currency)) {
            return amount * CurrencyExchangeRateConstants.EUR_TO_USD;
        }
        if (Currency.CHF.equals(currency)) {
            return amount * CurrencyExchangeRateConstants.CHF_TO_USD;
        }
        return amount;
    }
}
```

After refactoring this code, if we keep the current assertion, a couple of **VND
test cases** will likely fail due to adjustment tolerance. Much of a surprised, I retest the constants, and there is a relationship between type of conversion constants that they break themselves.

```
 @Test
public void testVNDtoUSDtoEUR() {
    double initVND = 25000000d;
    double vndToUSD = initVND * CurrencyExchangeRateConstants.VND_TO_USD;
    double vndToChf = initVND * CurrencyExchangeRateConstants.VND_TO_CHF;
    double vndToUsdToChf = vndToUSD * CurrencyExchangeRateConstants.USD_TO_CHF;
    assertEquals(vndToChf, vndToUsdToChf, FoodVendingTestConstant.EPSILON);
}
```
>java.lang.AssertionError: expected:<1083.5264538649603> but was:<1080.6065678696275>
	at org.junit.Assert.fail(Assert.java:88)
	at org.junit.Assert.failNotEquals(Assert.java:834)


>For the case VND to another type of currency, due to our new implementation, if it convert from VND to USD, then USD to CHF correctly, then the result will be acceptable.

We can then refactor of test case

`CurrencyConverterTest.java`
```
@Test
public void testConvertEURtoVND() {
    double eurAmount = 50;
    double expectedAmount = eurAmount * CurrencyExchangeRateConstants.EUR_TO_USD * CurrencyExchangeRateConstants.USD_TO_VND;
    double actualAmount = CurrencyConverter.exchangeCurrency(Currency.EUR, Currency.VND, eurAmount);
    assertEquals(expectedAmount, actualAmount, FoodVendingTestConstant.EPSILON);
}
```


It's time to rebuild the Food Vending Machine !

`GreenDuckFoodVendingMachine.java`
```
public class GreenDuckFoodVendingMachine extends FoodVendingMachine {

    public GreenDuckFoodVendingMachine(Currency currency) {
        super(currency);
    }

    @Override
    public void addBalance(Banknote note) {
        this.balance += CurrencyConverter.exchangeCurrency(note.getCurrency(), getCurrency(), note.getAmount());
    }

    @Override
    public Food getFood(int selectedIndex) {
        verifyValidFoodSelection(selectedIndex);

        double currentBalance = getBalance();
        Food selectedFood = foods.get(selectedIndex);

        this.balance -= CurrencyConverter.exchangeCurrency(selectedFood.getPriceTag().getCurrency(),
                getCurrency(), selectedFood.getPriceTag().getPrice());

        if (balance < 0) {
            System.out.println("In Your Dream");
            this.balance = currentBalance;
            selectedFood = null;
        } else {
            this.foods.remove(selectedIndex);
        }
        printBalance();
        return selectedFood;
    }
}
```



After built, don't forget to put the machine into the current test cases

`FoodVendingMachineTest.java`
```
@Before
public void setUp() {
    //foodVendingMachine = new MonkeyFoodVendingMachine(Currency.VND);
    foodVendingMachine = new GreenDuckFoodVendingMachine(Currency.VND);
}
```

## The Big Picture with a new Green Duck Food Vending Machine
![GreenDuckFoodVendingMachine](https://i.imgur.com/pIPNzau.png)

## What have we achieve so far using this design ?

> Single Responsibility Principle

A vending machine know what to do when user attempt to buy food, or add banknote. And a currency converter know how to convert different type of currencies. One man do one thing.

> Open-Closed Principle

By separating currency conversion stuff to a new class only responsible for this. The food vending machine doesn't have to be broken up to add a new type of currency, or the conversion rate changes.

I've been always love this quote from the book **HeadFirst Design Patterns**

> Identify the aspects of your application that vary and separate them from what stays the same.

- Thing that vary: The currency conversion rate, the new type of currency
- Thing that stay the same: The vending machine increase balance when a banknote is inserted, and decrease balance when a food is bought. This behavior will never changes if the currency conversion rate changes.

#### Something To Remember... :love_letter:
I got 4 submissions from my company fellow developers. They are Phuc, Tung, Nguyet and Binh. Couldn't tell how happy I was to see my challenge embraced by others. :smile_cat:  
![The review I got](https://i.imgur.com/Y1tlQYM.jpg)











