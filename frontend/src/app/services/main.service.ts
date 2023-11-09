import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { EMPTY, Observable, Subject, catchError, map } from "rxjs";
import { TagInfo } from "../models/tagInfo.model";


@Injectable ({
    providedIn: 'root'
  })
export class MainService{

  apiUrl = "http://localhost:8080";

    constructor(private http : HttpClient){}

    getAllTags(): Observable<TagInfo[]> {

        const headers= new HttpHeaders()
            .set('content-type', 'application/json')
            .set('Access-Control-Allow-Origin', '*');

        return this.http.get<TagInfo[]>(`${ this.apiUrl }/tag-storage`, {
            headers : headers
        });
    }

    getTagsHistory(): Observable<TagInfo[]> {

        const headers= new HttpHeaders()
            .set('content-type', 'application/json')
            .set('Access-Control-Allow-Origin', '*');

        return this.http.get<TagInfo[]>(`${ this.apiUrl }/historico`, {
            headers : headers
        });
    }

    getTagDetail(tagId: string): Observable<TagInfo[]> {

        const headers= new HttpHeaders()
            .set('content-type', 'application/json')
            .set('Access-Control-Allow-Origin', '*');

        return this.http.get<TagInfo[]>(`${ this.apiUrl }/historico/${tagId}`, {
            headers : headers
        });
    }


}