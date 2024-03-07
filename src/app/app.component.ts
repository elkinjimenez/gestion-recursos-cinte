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

  orderedWord: string[] = [];

  desorderedWord: string[] = [];

  constructor(
    private wordService: WordService,
  ) { }

  ngOnInit() {
    this.wordService.getWord().subscribe((data) => {
      if (data) {
        this.orderedWord = data[0].split('');
        this.desorderedWord = [...this.orderedWord];
        this.desorderedWord.sort(() => Math.random() - 0.5);
      }
    }, error => {
      alert('Error al consumir el servicio de consulta de palabras: ' + error);
    });
  }

}
