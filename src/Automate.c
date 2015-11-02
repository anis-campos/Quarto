/*
========================================================================
    Fichier     :   main.c
    Auteurs     :   DUFAITRE Hugo - DA SILVA CAMPOS Anis
    Date        :   05/02/15
    Description :   Automates de Programmation
========================================================================
*/



#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAILLE_MAX 1000

typedef struct mots Mots;

struct mots
{
    char libelle_mot[10];
    int type_mot;
};


enum Entrees
{
    ARTICLE,
    ADJECTIF,
    NOM_COMMUM,
    VERBE,
    NOM_PROPRE,
    POINT
} Entre_Act;

enum Etat
{
    ATTENTE_DEBUT,
    TROUVE_ARTICLE_1,
    TROUVE_NOM_1,
    TROUVE_VERBE,
    TROUVE_NOM_PROPRE_1,
    TROUVE_ARTICLE_2,
    TROUVE_NOM_2,
    TROUVE_NOM_PROPRE_2,
    ETAT_NOK,
    ETAT_OK
} Etat_Act;

enum Sorties
{
    ENCOURS,
    PHRASE_NOK = 8,
    PHRASE_OK
} Sortie_Act;


Mots dictionnaire[1000] =
{
    {"le", 0},
    {"chat", 2},
    {"souris", 2},
    {"mange", 3},
    {"la", 0},
    {"petite", 1},
    {".", 5},
    {"bleu", 1},
    {"dort", 3},
    {"julie", 4},
    {"joli", 1},
    {"joue", 3},
	{"Jean",4},
	{"Martin",4},
	{"verte",1},
	{"grosse",1},
	{"petit",1},
	{"blanc",1}
};

int Table_de_Transition[10][6];
int Table_de_Sorties[10];





void EcrireDico()
{
    FILE *file = fopen("dico.txt", "w+");
    int i=0,type, count = sizeof(dictionnaire) / sizeof(Mots);
    Mots *val = NULL;
    char *mot;
    for (i = 0; i < count; ++i)
    {
        val = &dictionnaire[i];
        mot = val->libelle_mot;
        type= val->type_mot;
        fprintf(file, "%s %d\n",mot,type );
        
    }
    
    fclose(file);
}

void ChargerDico()
{
    FILE *file = fopen("dico.txt", "r");
    if (file != NULL)
    {
        char *ligne;
        int i=0;
        Mots * mot;
        while (fgets(ligne, TAILLE_MAX, file) != NULL)
        {
            mot=&dictionnaire[i];
            sscanf(ligne,"%s %d\n",mot->libelle_mot,&(mot->type_mot));
            i++;
        }
        fclose(file);
    }
}

void ViderDico()
{
    
    FILE *file = fopen("dico.txt", "wb");
    fclose(file);
    
}


/*
 * Entrées :
 * Sorties :
 * Description : Initialisation des variables globales.
 */
void Init ()
{
	   int ET = 0, EN = 0;
    Sortie_Act = ENCOURS;
    Etat_Act = ATTENTE_DEBUT;
    //EcrireDico();
    //ChargerDico();
 
    for (ET = 0; ET < 10; ET++)
    {
        for (EN = 0; EN < 6; EN++)
        {
            Table_de_Transition[ET][EN] = ETAT_NOK;
        }
        Table_de_Sorties[ET] = ENCOURS;
    }
    Table_de_Sorties[ETAT_NOK] = PHRASE_NOK;
    Table_de_Sorties[ETAT_OK] = PHRASE_OK;
    
    Table_de_Transition[ATTENTE_DEBUT][ARTICLE] = TROUVE_ARTICLE_1;
    Table_de_Transition[ATTENTE_DEBUT][NOM_PROPRE] = TROUVE_NOM_PROPRE_1;
    Table_de_Transition[TROUVE_ARTICLE_1][ADJECTIF] = TROUVE_ARTICLE_1;
    Table_de_Transition[TROUVE_ARTICLE_1][NOM_COMMUM] = TROUVE_NOM_1;
    Table_de_Transition[TROUVE_NOM_1][ADJECTIF] = TROUVE_NOM_1;
    Table_de_Transition[TROUVE_NOM_1][VERBE] = TROUVE_VERBE;
    Table_de_Transition[TROUVE_NOM_PROPRE_1][VERBE] = TROUVE_VERBE;
    Table_de_Transition[TROUVE_VERBE][ARTICLE] = TROUVE_ARTICLE_2;
    Table_de_Transition[TROUVE_VERBE][POINT] = ETAT_OK;
    Table_de_Transition[TROUVE_VERBE][NOM_PROPRE] = TROUVE_NOM_PROPRE_2;
    Table_de_Transition[TROUVE_NOM_PROPRE_2][POINT] = ETAT_OK;
    Table_de_Transition[TROUVE_ARTICLE_2][ADJECTIF] = TROUVE_ARTICLE_2;
    Table_de_Transition[TROUVE_ARTICLE_2][NOM_COMMUM] = TROUVE_NOM_2;
    Table_de_Transition[TROUVE_NOM_2][ADJECTIF] = TROUVE_NOM_2;
    Table_de_Transition[TROUVE_NOM_2][POINT] = ETAT_OK;
    
    
}



/*
 * Entrées : Le mot à tester
 * Sorties :
 * Description : Utilise le dictionnaire, l'etat actuel et la matrice de transition pour determiner si la phrase est toujours corecte.
 */
void syntaxe(char *mot)
{
    int i = 0;
    int TypeMot = -1;
    
    
    
    for ( i = 0; i < sizeof(dictionnaire) / sizeof(Mots) ; ++i)
    {
        if ( strcmp(dictionnaire[i].libelle_mot, mot) == 0)
        {
            TypeMot = dictionnaire[i].type_mot;
        }
        
    }
    if (TypeMot == -1)
    {
		printf("mot inexistant dans le dictionnaire : %s",mot);
		getc(stdin);
		Etat_Act = ETAT_NOK;
        return;
    }
    else
    {
        Etat_Act = Table_de_Transition[Etat_Act][TypeMot];
    }
    
    
}

/*
 * Entrées :
 * Sorties :
 * Description : Afficher si la phrase est correcte/incorrecte/incomplete.
 */
void actionSelonSortie()
{
    switch (Sortie_Act)
    {
            
        case PHRASE_OK:
            printf("	->	Phrase Correcte\n");
            break;
            
        case PHRASE_NOK:
            printf("	->	Phrase Non Correcte\n");
            break;
            
        case ENCOURS:
            printf("%s\n", "	->	Phrase Incompelete");
            break;
    }
    printf("\n");
}

/*
 * Entrées : string - la chaine a diviser
 position - l'index de debut de la  division
 length - la taille de la sous-chaine
 * Sorties : pointeu de la sous-chaine
 * Description : Permet d'extraire une partie d'une chaine
 */
char *substring(char *string, int position, int length)
{
    char *pointer;
    int c;
    
    pointer = malloc(length - 1);
    
    if (pointer == NULL)
    {
        exit(EXIT_FAILURE);
    }
    
    for ( c = 0; c < length; c++)
    {
        *(pointer + c) = *((string + position - 1) + c);
        
    }
    *(pointer + c) = '\0';
    return pointer;
}

/*
 * Entrées : la phrase à modifier
 * Sorties :
 * Description : Permet d'ajouter un espace avant le point.
 */
void espacerPoint(char *phrase)
{
    char *tmp;
    long taille;
    char *point;
	point = strchr(phrase, '.');
    taille = point - phrase;
    
  
    if (point != NULL)
    {
        tmp = substring(phrase, 1, (int)taille);
        
        strcpy(phrase, tmp);
        strcat(phrase, " .");
        //free(tmp);
    }
    
}


/*
    REMARQUES:

    - Le dictionnaire est codé en dur.
    - La phrase est codée est en dur.
*/
int main()
{

    FILE *fichier;
	char chaine[TAILLE_MAX] = "";
    char delim [] = " ,;";
    char *mot;
    fichier = fopen("phrases.txt", "r");
    if (fichier == NULL) return -1;
   
    while (fgets(chaine, TAILLE_MAX, fichier) != NULL)
    {

        Init();
        printf("La phrase : %s", chaine);
        espacerPoint(chaine);
        mot = strtok(chaine, delim);

        while (mot != NULL && Sortie_Act == ENCOURS)
        {

            syntaxe(mot);
            Sortie_Act = Table_de_Sorties[Etat_Act];
            mot = strtok(NULL, delim);
        }
		free(mot);
        actionSelonSortie();

    }
    fclose(fichier);



    return 0;
}