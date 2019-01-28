# Application RollingStone
## RollingStone est un petit jeu qui consiste à déplacer une pierre dans un labyrinthe d'un point A à un point B.

> Le but de ce TP est de se familiariser avec le Canvas et les Gestures.

1. Créer une application basée sur le template **'Empty Activity'**.
2. Créer une classe **BoardView** qui hérite de l'objet **View**.
3. La classe **BoardView** va servir de support pour le tracé du plateau.

## BoardView

La classe BoardView hérite donc de l'objet View. L'objet View dispose d'une méthode 
[onDraw(Canvas canvas)](https://developer.android.com/reference/android/view/View.html#onDraw(android.graphics.Canvas))
qu'il faudra surcharger afin de dessiner dedans.

```java
  @Override
  onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
```

A la création de l'objet BoardView, le système appelera la méthode ```onDraw```  automatiquement. Par contre, pour forcer un nouveau tracé, il faudra appeler la méthode [invalidate()](https://developer.android.com/reference/android/view/View.html#invalidate())

### Le widget BoardView sera intégré dans le constraintLayout de l'Activity de la façon suivante :

> Nous integrerons directement l'objet dans le ConstraintLayout à l'aide de ```ConstraintSet```.

```java
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ConstraintLayout lLayout = findViewById(R.id.mainConstraint);

    BoardView lView = new BoardView(this);
    // https://stackoverflow.com/questions/8937380/how-to-set-id-of-dynamic-created-layout
    lView.setId(R.id.board_view);
    lLayout.addView(lView,0);

    // Get constraint tool
    ConstraintSet set = new ConstraintSet();
    set.clone(lLayout);

    // Set constraints
    set.connect(lView.getId(), ConstraintSet.TOP, lLayout.getId(), ConstraintSet.TOP, 0);
    set.connect(lView.getId(), ConstraintSet.BOTTOM, lLayout.getId(), ConstraintSet.BOTTOM, 0);
    set.connect(lView.getId(), ConstraintSet.LEFT, lLayout.getId(), ConstraintSet.LEFT, 0);
    set.connect(lView.getId(), ConstraintSet.RIGHT, lLayout.getId(), ConstraintSet.RIGHT, 0);
    set.applyTo(lLayout);
  }
```
> Pour info : La version xml correspondant au code précédent.

```xml
<View
  android:id="@+id/board_view"
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  app:layout_constraintBottom_toBottomOf="parent"
  app:layout_constraintEnd_toEndOf="parent"
  app:layout_constraintStart_toStartOf="parent"
  app:layout_constraintTop_toTopOf="parent"/>

```

## Description des labyrinthes:
> Les labyrinthes seront des carrés de 10x10, ils devront s'afficher au centre du téléphone et sur toute la largeur.

```
"##########"
"# ##B #  #"
"#  ##    #"
"#    #   #"
"# #  #   #"
"# ####   #"
"#        #"
"# #  ##  #"
"#A#      #"
"##########"
```

Les labyrinthes seront stockés de la manière suivante :
```java
String labyrinthe1 = "########### ##B #  ##  ##    ##    #   ## #  #   ## ####   ##        ## #  ##  ##A#      ###########"
String labyrinthe2 = ...
```

### Les murs et la pierre seront récupérés dans le fichier tiles.png se trouvant dans le projet sous **res/raw**.
### Ne pas découper le fichier tiles.png en plusieurs fichiers.

## Déplacement de la pierre
Pour déplacer la pierre vers le haut, le bas, la gauche et la droite, tu utiliseras le **Swipe Gesture**

## Documentation:
### Interaction utilisateur
* [Use touch gestures](https://developer.android.com/training/gestures/)
* [Sensors](https://developer.android.com/guide/topics/sensors/)

### Dessiner dans un canvas
* [Canvas](https://developer.android.com/reference/android/graphics/Canvas)
* [Paint](https://developer.android.com/reference/android/graphics/Paint)
* [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap)
