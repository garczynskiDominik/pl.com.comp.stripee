Stowrzyłem kontroler InvoiceItemController który, pozawala na dodawanie produktów do faktur, jest to warunek wystawienia faktur.
Według dokumentacji biblioteki Stripe nie da sie wystawić faktury bez dodania wczesniej produktów.
Drugi kontroler czyli InvoiceController również posiada dwa endpointy, pierwszy PUT do tworzenia nowej faktury na podstawie dodanych produktów,
drugi GET do pobierania wszystkich faktur z ustawionym limitem ilości.

APIKEY wydzieliłem do pliku propetris.

Logika została wydzielona do odpowiednich serwisów gdzie wykorzystałem APIKEY oraz klucz użytkowanika do wykonania metoda na fakurach i produktach.
Wyniki zwrócone z Stiripe przekonwertowałem na obiekty DTO które, są zwracane przez endpointy W serwisach wykorzystałem biblioteke Log4j do logowania jak 
wykonywały sie metody. Dodatkowo używamłem Lombooka dla polepszenia czytelności kodu oraz Swaggera do testowania endpointów. Nie obyło sie również bez Postmana 
którym testowałem zapytania do Stripe.

