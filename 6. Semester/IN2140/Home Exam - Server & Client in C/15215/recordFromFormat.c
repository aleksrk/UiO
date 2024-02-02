/*
 * This file implements two functions that read XML and binary information from a buffer,
 * respectively, and return pointers to Record or NULL.
 *
 * *** YOU MUST IMPLEMENT THESE FUNCTIONS ***
 *
 * The parameters and return values of the existing functions must not be changed.
 * You can add function, definition etc. as required.
 */
#include "recordFromFormat.h"
#include <string.h>
#include <stdlib.h>
#include <arpa/inet.h>

Record* XMLtoRecord( char* buffer, int bufSize, int* bytesread )
{
    Record *rec = newRecord();
    char *start = NULL;
    char *end = NULL;
    char value[100];

    // Find source and assign to Record struct
    start = strstr(buffer, "<source=\"");
    if (start) {
        start += strlen("<source=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        setSource(rec, value[0]);
    }

    // Find destination and assign to Record struct
    start = strstr(buffer, "<dest=\"");
    if (start) {
        start += strlen("<dest=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        setDest(rec, value[0]);
    }

    // Find username and assign to record struct
    start = strstr(buffer, "<username=\"");
    if (start) {
        start += strlen("<username=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        setUsername(rec, value);
    }    

    // Find id and assign to record struct
    start = strstr(buffer, "<id=\"");
    if (start) {
        start += strlen("<id=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        uint32_t id = atoi(value);
        setId(rec, id);
    }  
    // Find group and assign to record struct
    start = strstr(buffer, "<group=\"");
    if (start) {
        start += strlen("<group=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        uint32_t group = atoi(value);
        setGroup(rec, group);
    } 
    // Find semester and assign to record struct
    start = strstr(buffer, "<semester=\"");
    if (start) {
        start += strlen("<semester=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        uint8_t semester = atoi(value);
        setSemester(rec, semester);
    } 
    // Find grade and assign to record struct
    start = strstr(buffer, "<grade=\"");
    if (start) {
        start += strlen("<grade=\"");
        end = strchr(start, '\"');
        strncpy(value, start, end - start);
        value[end - start] = '\0';
        if (strcmp(value, "None") == 0) {
            setGrade(rec, Grade_None);
        } else if (strcmp(value, "Bachelor") == 0) {
            setGrade(rec, Grade_Bachelor);
        } else if (strcmp(value, "Master") == 0) {
            setGrade(rec, Grade_Master);
        } else if (strcmp(value, "Phd") == 0) {
            setGrade(rec, Grade_PhD);
        }
        
    } 
    // Find the courses and assign to record struct
    start = strstr(buffer, "<courses>");
    if (start) {
        uint16_t courseBit = 0;
        if (strstr(buffer, "IN1000") != 0) courseBit |= Course_IN1000;
        if (strstr(buffer, "IN1010") != 0) courseBit |= Course_IN1010;
        if (strstr(buffer, "IN1020") != 0) courseBit |= Course_IN1020;
        if (strstr(buffer, "IN1030") != 0) courseBit |= Course_IN1030;
        if (strstr(buffer, "IN1050") != 0) courseBit |= Course_IN1050;
        if (strstr(buffer, "IN1060") != 0) courseBit |= Course_IN1060;
        if (strstr(buffer, "IN1080") != 0) courseBit |= Course_IN1080;
        if (strstr(buffer, "IN1140") != 0) courseBit |= Course_IN1140;
        if (strstr(buffer, "IN1150") != 0) courseBit |= Course_IN1150;
        if (strstr(buffer, "IN1900") != 0) courseBit |= Course_IN1900;
        if (strstr(buffer, "IN1910") != 0) courseBit |= Course_IN1910;
        setCourse(rec, courseBit);   
    }

    *bytesread = bufSize;
    return rec;
}

Record* BinaryToRecord( char* buffer, int bufSize, int* bytesread )
{
    Record *rec = newRecord();

    uint8_t flags = buffer[0];
    int rc = 1;

    // Check flags and assign to record struct
    if (flags & FLAG_SRC) {setSource(rec, buffer[rc]); rc+=1;}
    if (flags & FLAG_DST) {setDest(rec, buffer[rc]); rc+=1;}
    if (flags & FLAG_USERNAME) {
        int len;
        memcpy(&len, buffer + rc, 4);
        len = ntohl(len);
        rc += 4;

        // Read in the username, then free the memory used
        char *username = malloc(len + 1);
        memcpy(username, buffer + rc, len);
        username[len] = 0;
        setUsername(rec, username);
        free(username);

        rc += len;
    }
    if (flags & FLAG_ID) {
        uint32_t id;
        memcpy(&id, buffer + rc, 4);
        id = ntohl(id);
        setId(rec, id);
        rc += 4;
    }
    if (flags & FLAG_GROUP) {
        uint32_t group;
        memcpy(&group, &buffer[rc], 4);
        group = ntohl(group);
        setGroup(rec, group);
        rc += 4;
    }
    if (flags & FLAG_SEMESTER) {
        uint8_t semester = buffer[rc];
        setSemester(rec, semester);
        rc += 1;
    }
    if (flags & FLAG_GRADE) {setGrade(rec, buffer[rc]); rc += 1;}
    if (flags & FLAG_COURSES) {
        uint16_t courses;
        memcpy(&courses, &buffer[rc], 2);
        courses = ntohs(courses);
        setCourse(rec, courses);
        rc += 2;
    }
    *bytesread = rc;

    return rec;
}

