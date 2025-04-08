#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct {
    char showId[];
    char type[];
    char title[];
    char director[];
    char cast[];
    char country[];
    Date dateAdded;
    int releaseYear;
    char rating[];
    char duration[];
    char listedIn[];
} Show;

void setShowId(Show *show, char *id) {
    strcpy(show->showId, id);
}

void setType(Show *show, char *type) {
    strcpy(show->type, type);
}

void setTitle(Show *show, char *title) {
    strcpy(show->title, title);
}

void setDirector(Show *show, char *director) {
    strcpy(show->director, director);
}

void setCast(Show *show, char *cast) {
    strcpy(show->cast, cast);
}

void setCountry(Show *show, char *country) {
    strcpy(show->country, country);
}

void setDateAdded(Show *show, Date *dateAdded) {
    show->dateAdded = *dateAdded;
}

void setReleaseYear(Show *show, int *releaseYear) {
    strcpy(show->releaseYear, releaseYear);
}

void setRating(Show *show, char *rating) {
    strcpy(show->rating, rating);
}

void setDuration(Show *show, char *duration) {
    strcpy(show->duration, duration);
}

void setListedIn(Show *show, char *listedIn) {
    strcpy(show->listedIn, listedIn);
}

char[] getShowId(Show *show) {
    return show->showId;
}

char[] getType(Show *show) {
    return show->type;
}

char[] getTitle(Show *show) {
    return show->title;
}

char[] getDirector(Show *show) {
    return show->director;
}

char[] getCast(Show *show) {
    return show->cast;
}

char[] getCountry(Show *show) {
    return show->country;
}

Date getDateAdded(Show *show) {
    return show->dateAdded;
}

int getReleaseYear(Show *show) {
    return show->releaseYear;
}

char[] getRating(Show *show) {
    return show->rating;
}

char[] getDuration(Show *show) {
    return show->duration;
}

char[] getListedIn(Show *show) {
    return show->listedIn;
}

void imprimir(Show* show) {
    printf("=> %s ", getShowId(show));
    printf("## %s ", getType(show));
    printf("## %s ", getTitle(show));
    printf("## %s ", getDirector(show));
    printf("## %s ", getCast(show));
    printf("## %s ", getCountry(show));
    printf("## %d/%d/%d ", show->dateAdded.day, show->dateAdded.month, show->dateAdded.year);
    printf("## %d ", getReleaseYear(show));
    printf("## %s ", getRating(show));
    printf("## %s ", getDuration(show));
    printf("## %s ", getListedIn(show));
}

