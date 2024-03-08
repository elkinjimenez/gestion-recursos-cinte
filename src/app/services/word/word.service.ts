import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WordService {

  private URL = "https://random-word-api.herokuapp.com/word?lang=es&number=10";

  constructor(
    private http: HttpClient
  ) { }

  getWord() {
    return this.http.get<string[]>(this.URL);
  }
}
