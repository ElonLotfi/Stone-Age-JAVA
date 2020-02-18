#Sujet : le jeu Stone Age (L'Âge de Pierre)

#Il s'agit de réaliser une version électronique du jeu "Stone Age".

#Il faut pour cela réaliser les fonctionnalités suivantes :

    représentation de jeu (représentation du plateau, des zones,  des cartes,  comptage des points, validité des coups, etc.). 
    moteur de jeu (mise en place, gestion des pioches, actions, détermination des points, de la fin de la partie...).
    robots de jeu, en partant d'une version très simple à des stratégies de jeu plus sophistiqués (chaque nouvel élément intégré dans votre moteur de jeu et dans la représentation doit être utilisée par un robot)
    simulation de parties entre 2 robots et entre 3 ou 4 robots, comptage des points, des victoires et classement entre vos robots.
    visualisation de l'état du jeu (en fin de partie pour commencer, tout au long de la partie ensuite). Cette visualisation devra être textuelle. Une version graphique N'EST PAS demandée, car impossible dans le temps imparti.
    Il est demandé deux exécutions (c.f. les outils pour configurer le pom) : 
        [id dans le pom.xml : partie] : une exécution  d'une partie, avec le déroulé visible et compréhensible
        [id dans le pom.xml : stat] : une exécution de 500 parties avec vos différents robots/IA, sans autres sorties textuelles que le avec résumé global : nombre de victoire, moyenne des points.
        le nombre de joueur sera un paramètre de l'exécution maven

(warning) Il ne s'agit pas de réaliser une version interactive de ce jeu (aucun joueur humain).

(warning) La priorité est donnée à une version avec 2 robots intelligents (instanciés plusieurs fois), plutôt qu'à 3 ou 4 robots assez stupides.




# Pour lancer la partie avec le déroulé du jeu :
mvn exec:java@partie 
# Pour lancer les stats :
mvn exec:java@stat


# Itération 1 - DEADLINE 25/10

Description : Les joueurs peuvent jouer avec un bot aléatoire et peuvent  placer un figurine dans un champs de ressource ( bois ,pierre ,or  et argile ) .La partie se termine lorsque tous les joueurs place  un figurine dans un champs .

# Itération 2 + 3 - DEADLINE 15/11 déplacé à itération 3

Description : Les joueurs(4 joueurs) peuvent placer des figurine dans les champs  (riviere,forêt,glaisière,carrière et chasse)  et peuvent collecter les ressources . La partie se termine lorsque les joueurs ont collecter les ressources. Le jeu détermine le gagnant. 


# Itération 4 - DEADLINE 22/11
Description : Les robots peuvent augmenter leurs niveau d'agriculture et peuvent nourrir ses figurines. La partie se termine lorsque les joueurs ont collecter les ressources. Le jeu détermine le gagnant.


# Itération 5 - DEADLINE 29/11

Description : les robots peuvent poser leurs personnages sur le plateau , réserver et acheter des bâtiments avec ressources imposées .La partie se termine si au moins l'une des tuiles batiments est vide .

# Itération 6 - DEADLINE 06/12

Description : Ajouter la partie statistique pour 500 parties .

# Itération 7 - DEADLINE 15/12

Description : Création d'un robot intelligent permettant de gagner le maximum de parties (SmartBot). Création un robot intelligent permettant de gagner le maximum de nourritures (HunterBot). La partie se termine si au moins l'une des tuiles batiments est vide . le vainqueur est celui qui obtient le score le plus élevé, en cas d'égalité entre deux robots, celui qui obtient le plus haut niveau d'agriculture est le vainqueur .






