import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WordService } from './services/word/word.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  wordListing = [
    'gatos',
    'perros',
    'ladrillo',
    'iglesia',
    'colorido',
    'árboles',
    'lapicero',
    'reloj',
    'ciudades',
    'general',
    'clausura'
  ]

  isDisabled: boolean[] = [];

  orderedWord: string[] = [];

  desorderedWord: string[] = [];

  validateWord: string[] = [];

  constructor(
    private wordService: WordService,
  ) {
    this.randomWord();
  }

  ngOnInit() { }

  ngAfterViewInit() {
    this.getWordsApi();
  }

  public randomWord() {
    this.isDisabled = [];
    this.validateWord = [];
    this.wordListing = this.wordListing.sort(() => Math.random() - 0.5);
    this.orderedWord = this.wordListing[0].split('');
    this.wordListing.shift();
    this.desorderedWord = [...this.orderedWord];
    this.desorderedWord.sort(() => Math.random() - 0.5);
    if (this.wordListing.length < 5)
      this.getWordsApi();

  }

  private getWordsApi() {
    this.wordService.getWord().subscribe((data: string[]) => {
      if (data && data.length > 0) {
        data.forEach(word => {
          if (/^[^\s.!#]{4,8}$/.test(word)) {
            this.wordListing.push(word);
          }
        })
      }
    }, error => {
      alert('Error al consumir el servicio de consulta de palabras: ' + error);
    });
  }

  public addLetter(letter: string, index: number) {
    this.validateWord.push(letter);
    this.isDisabled[index] = true;
  }

}
