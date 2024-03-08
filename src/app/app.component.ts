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

  isDisabled: boolean[] = [];

  orderedWord: string[] = [];

  desorderedWord: string[] = [];

  validateWord: string[] = [];

  modal = {} as Modal;

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
    this.wordService.wordListing = this.wordService.wordListing.sort(() => Math.random() - 0.5);
    this.orderedWord = this.wordService.wordListing[0].split('');
    this.wordService.wordListing.shift();
    this.desorderedWord = [...this.orderedWord];
    this.desorderedWord.sort(() => Math.random() - 0.5);
    if (this.wordService.wordListing.length < 10)
      this.getWordsApi();
  }

  private getWordsApi() {
    this.wordService.getWord().subscribe((data: string[]) => {
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
    this.validateWord.push(letter);
    this.isDisabled[index] = true;
    this.validateGame();
  }

  public clean() {
    this.isDisabled = [];
    this.validateWord = [];
  }

  validateGame() {
    if (this.validateWord.length == this.orderedWord.length) {
      if (this.validateWord.toString() == this.orderedWord.toString()) {
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

}

interface Modal {
  title: string;
  msg: string;
  type: TYPE_MODAL;
}

type TYPE_MODAL = 'SUCCESS' | 'DANGER';
