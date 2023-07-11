# Doc

https://www.w3schools.com/java/default.asp

https://learning.oreilly.com/library/view/java-in-a/9781098130992/
https://github.com/kittylyst/javanut8
https://oreil.ly/java-in-a-nutshell-8e

[Head First Java](https://docs.google.com/file/d/0BwxUBHTpU9kCU0xubVhyYlp0bWc/view?resourcekey=0-sk68B4dt12P8MPoLieNBBA)

# Poo Java:

-classes: moule 
-attributs/méthodes

Personne p1 = new Personne(); <- () = tesla rouge, jantes chromées: constructeur par défaut

## Classe : attribut/méthode statiques

## Instance: peut accéder à tout

p1.getAge();

- redéfinition ex toString (par défaut classe@addresse ) -> @Override 

java.lang

- portée des variables entre {}

- Valeur par défaut : string Null, int 0

ATTENTION si on définit un autre constructeur on réutilise le même @Override toString()

## Polymorphisme :

contrat : classe fille se comporte comme mère :

extends / super(name,args)


- Personne eleyes = new Elyes("Elyes", 21 ,"addresse"); ok

ATTENTION Aurelien aurelien1 = new Personne("Aurelien",22) NOK  -> Classe mère ne peut pas se comporter comme classe Fille


- Surcharge: nom ou arguments différents mais même classe

# Généricité / Interface : pb de l'imprimante

List l = new Vector; -> à privilégier

vs

Vector v = new Vector();

# Itérateurs

## Externe

- Iterator : list.iterator
- while
- for
- for (Personne p : list) (foreach)

## Interne

- Stream : entiers.stream().forEach(e -> System.out.prinln(e));

-> *Lambda* : Interface fonctionnelle contenant 1 seule méthode
