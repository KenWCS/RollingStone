# Application RollingStone
## Trucs et Astuces de la solution

### 1. Mise en évidence de l'importance du dpi à travers Metrics.mDensity
> L'usage de la densité n'est pas obligatoire dans la solution mais c'est un bon contexte pour l'aborder.
Le fichier tiles.png contient des tuiles de 70x70 pixels. Une fois chargé dans le téléphone, nous remarquons que la taille des tuiles a été multiplié par un coefficient différent selon les téléphones. Ce coefficient est le Dpi.
Par défaut, Android dispose du dossier res/bitmap qui se charge de granuler la taille des images en fonction des téléphones grâce aux sous dossiers mdpi, hdpi, xhdpi, xxhdpi et xxxhdpi.

Dès lors que nous chargeons une image programmatiquement, nous pouvons être ammener à gérer le dpi. Pour ce faire, nous utilisons la propriété **density** se trouvant sous ```getResources().getDisplayMetrics()``` de l'Activity.

### 2. Remplissage du ConstraintLayout programmatiquement
> Le designer est un outil pratique pour positionner les widgets les uns par rapport aux autres. Des quêtes montrent comment utiliser le designer pour faire l'UI, d'autres montrent comment coder l'UI à l'aide de LinearLayout, FrameLayout, GridLayout, ... Quand est-il du ConstraintLayout ?

Certains élèves qui n'ont rencontrés aucune difficulté à faire la quête **Matrice UI** n'ont pas été capable d'insérer programmatiquement un widget dans un ConstraintLayout tellement l'approche est différente des autres Layouts.
Dans l'exercice, nous abordons le sujet par l'exemple en montrant comment insérer l'objet *BoardView* héritant de *View* dans le *ConstraintLayout* grâce à la classe [ConstraintSet](https://developer.android.com/reference/android/support/constraint/ConstraintSet).

### 3. Passage par référence
> La notion de passage par reference ou par valeur est difficile à assimiler par les élèves n'ayant pas fait de programmation en C/C++ (&). Sous Kotlin, le problème n'existera plus car il n'y a plus de type primitif.

Dans la solution proposée, nous utilisons un objet Point pour définir la position de la pierre tout au long des déplacements. Cet objet est passé par référence de la classe MainActivity à la classe BoardView. Ceci nous permet de modifier les attributs de l'objet dans une classe et de les lire dans une autre sans devoir faire de méthodes Get/Set.

### 4. Asynchronisme avec AsyncTask
> La création du plateau peut prendre un certain temps selon sa taille. L'asynchronisme n'est pas un reflexe car aucun des élèves n'y a pensé. Et pourtant, nous nous trouvons dans un cas de figure qui pourrait bloquer le Thread UI.

Que choisir Runnable ou AsyncTask ?
* l'AsyncTask est plus lourd à écrire mais permet, grâce à ses interfaces, de connaitre la progression du traitement. Nous pouvons envisager une ProgressBar qui évoluerait durant la construction du plateau.

### 5. Héritage ou composition
> La question revient assez souvent lorsque l'on est en charge de l'architecture d'une application. Dans notre solution, la question s'est posé pour la classe BitmapData qui, au départ, était une variable membre de la classe BitmapBuilder (composition).

Le code évoluant, l'architecture en place ne semblait pas propre:
* Déclaration des variables membres de BitmapData avec une portée **public** pour un accès rapide aux données.
* Utilisation des variables de BitmapData dans BitmapBuilder et BitmapWorkerTask en mode passe-plat.

L'héritage s'est imposé comme solution au problème architectural.

#### Définition d'un Pattern
> Un Pattern est un arrangement caractéristique de modules, reconnu comme bonne pratique en réponse à un problème de conception d'un logiciel. **Problème/Solution**
* Dans notre cas, le pattern Héritage a répondu à notre problème de conception.

### 6. Les Patterns

#### Builder/NestedBuilder
Nous avons utilisé un mode hybride du pattern NestedBuilder pour construire les instances de classe Metrics et BoardView.

> Comment faire :
* Il suffit de retourner l'instance de l'objet dans chaque méthode Setter de la classe.

> Dans quel but :
* Le but est de construire rapidement un objet en évitant les constructeurs à ralonge.

#### Singleton
La classe Metrics doit être accessible de partout, elle contient des variables membres et son contenu peut évoluer. Basculer tout son contenu en static pourrait être moins agréable à maintenir et la pile d'execution serait solicité alors qu'on pourrait l'éviter. La solution au problème est donc le Singleton.

#### Observer
Pour la création du plateau, nous utilisons le listener BitmapBuilderListener afin de remonter les informations à l'activité. Ce pattern évite une liaison forte entre deux entités qui sont indépendantes.
