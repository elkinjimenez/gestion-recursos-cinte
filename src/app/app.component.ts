import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { WordService } from './services/word/word.service';

declare var $: any;

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  oneHelp: boolean | null = true;

  isDisabled: boolean[] = [];

  orderedWord: string[] = [];

  desorderedWord: string[] = [];

  validateWord: string[] = [];

  modal = {} as Modal;

  score: number = 0;

  constructor(
    private wordService: WordService,
  ) {
    this.myScore();
  }

  ngOnInit() { }

  private myScore() {
    const score = localStorage.getItem(btoa('score'));
    this.score = score ? parseInt(atob(score)) : 0;
    this.randomWord();
  }

  ngAfterViewInit() {
    this.getWordsApi();
  }

  public randomWord() {
    this.clean();
    this.wordService.wordListing = this.wordService.wordListing.sort(() => Math.random() - 0.5);
    this.orderedWord = this.wordService.wordListing[0].split('');
    this.wordService.wordListing.shift();
    this.desorderedWord = [...this.capitalizeFirstLetters(this.orderedWord)];
    this.desorderedWord.sort(() => Math.random() - 0.5);
    if (this.wordService.wordListing.length < 10)
      this.getWordsApi();
  }

  private capitalizeFirstLetters(word: string[]) {
    const n = (this.score) ? ((this.score < 10) ? 1 : (this.score < 20) ? 2 : (this.score < 30) ? 3 : (this.score < 40) ? 4 : 0) : 0;
    for (let index = 0; index < n; index++) {
      if (word && word[index])
        word[index] = word[index].toUpperCase()
      else break;
    }
    return word;
  }

  private getWordsApi() {
    const length = this.score ? (this.score < 10) ? 4 : (this.score < 25) ? 5 : (this.score < 40) ? 6 : (this.score < 55) ? 7 : (this.score < 70) ? 8 : (this.score >= 80) ? 9 : 4 : 4;
    this.wordService.getWord(length).subscribe((data: string[]) => {
      if (data && data.length > 0) {
        data.forEach(word => {
          if (/^[^\s.!#]{4,8}$/.test(word)) {
            this.wordService.wordListing.push(word);
          }
        })
      }
    }, error => {
      alert('Error al consumir el servicio de consulta de palabras: ' + error);
    });
  }

  public addLetter(letter: string, index: number) {
    this.oneHelp = false;
    this.validateWord.push(letter);
    this.isDisabled[index] = true;
    this.validateGame();
  }

  public clean() {
    this.isDisabled = [];
    this.validateWord = [];
    this.oneHelp = true;
  }

  validateGame() {
    if (this.validateWord.length == this.orderedWord.length) {
      if (this.validateWord.toString() == this.orderedWord.toString()) {
        this.operationsScore(2, false);
        this.showModalResponse(
          this.wordService.listTitleVictory[Math.floor(Math.random() * this.wordService.listTitleVictory.length)],
          `${this.wordService.listMsgVictory[Math.floor(Math.random() * this.wordService.listMsgVictory.length)]} Sigue así...`,
          'SUCCESS');
        setTimeout(() => {
          this.randomWord();
        }, 1000);
      } else {
        this.showModalResponse(
          this.wordService.listTitleLose[Math.floor(Math.random() * this.wordService.listTitleLose.length)],
          `${this.wordService.listMsgLose[Math.floor(Math.random() * this.wordService.listMsgVictory.length)]} Intenta de nuevo...`,
          'DANGER');
        setTimeout(() => {
          this.clean();
        }, 1000);
      }
    }
  }

  showModalResponse(title: string, msg: string, type: TYPE_MODAL) {
    this.modal = { msg, title, type }
    $('#modalResponse').modal('show');
  }

  public help() {
    if (this.oneHelp) {
      this.operationsScore(-1, false);
      this.oneHelp = null;
      const index = Math.floor(Math.random() * this.orderedWord.length);
      this.validateWord[index] = this.orderedWord[index];
      for (const key in this.orderedWord)
        this.isDisabled[key] = true;
      setTimeout(() => {
        this.validateWord = [];
        for (const key in this.orderedWord)
          this.isDisabled[key] = false;
        this.oneHelp = true;
      }, 4000);
    }
  }

  public operationsScore(value: number, isJump: boolean) {
    this.score += value;
    if (this.score < 0) {
      isJump
        ? this.showModalResponse('¡Uy!, ¿qué pasó?', 'Nos debes, ¿cuándo vas a pagar?', 'ANGRY')
        : null;
      this.score = 0;
    }
    localStorage.setItem(btoa('score'), btoa(String(this.score)));
  }

  public info() {
    this.showModalResponse('El reglamento', `
      🟢 +2 puntos por cada palabra correcta. <br>
      🔴 - 1 punto por cada ayuda.<br>
      🔴 - 1 punto por saltar la palabra.`,
      'INFO');
  }

}

interface Modal {
  title: string;
  msg: string;
  type: TYPE_MODAL;
}

type TYPE_MODAL = 'SUCCESS' | 'DANGER' | 'ANGRY' | 'INFO';
