import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WordService {

  private URL = "https://random-word-api.herokuapp.com/word?lang=es&number=10";

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

  listTitleVictory = [
    '¡Victoria épica!',
    '¡Triunfo legendario!',
    '¡Dominación total!',
    '¡Hazaña gloriosa!',
    '¡Apoteósico!',
    '¡Invencible!',
    '¡Imparable!',
    '¡Arrasador!',
    '¡Demoledor!',
    '¡Sublime!',
    '¡Objetivo cumplido!',
    '¡Misión completada!',
    '¡Éxito total!',
    '¡Felicidades!',
    '¡Buen trabajo!',
    '¡Enhorabuena!',
  ]

  listMsgVictory = [
    '¡Game Over, suckers!',
    '¡Soy imparable, ja ja ja!',
    '¡Otra victoria para mi colección!',
    '¡Nadie me puede vencer!',
    '¡Soy el/la rey/reina del juego!',
    'Partida ganada.',
    'Victoria conseguida.',
    'Objetivo logrado.',
    'Fin del juego.',
    'Resultado: victoria.',
    '¡Lo conseguimos!',
    '¡Victoria aplastante!',
    '¡Triunfo rotundo!',
    '¡Éxito abrumador!',
    '¡Fiesta de campeones!',
    '¡Alegría total!',
    '¡Lo mejor está por venir!',
    '¡El cielo es el límite!',
    '¡Habemus campeón!',
  ]

  listTitleLose = [
    '¡Desastre total!',
    '¡Fracaso épico!',
    '¡Derrota aplastante!',
    '¡Humillación monumental!',
    '¡Fin de la racha!',
    '¡Decepción total!',
    '¡Tristeza y amargura!',
    '¡No lo puedo creer!',
    '¡Qué mala suerte!',
    '¡Estuvo cerca!',
    '¡Desmoralizados!',
    '¡Desanimados!',
    '¡Arrasados!',
    '¡Desesperados!',
    '¡Frustrados!',
    '¡Incompetentes!',
    '¡Desilusionados!',
    '¡Abatidos!',
    '¡Hundidos!',
    '¡Deshechos!',
  ]

  listMsgLose = [
    '¡Game Over, noobs!',
    '¡A llorar a la casita!',
    '¡Soy demasiado malo para este juego!',
    '¡Sus lágrimas son mi elixir!',
    '¡Dominado por completo!',
    '¡Fin de la partida, llorón!',
    '¡Nos vemos en la próxima, perdedor!',
    '¡Aquí no hay rival bueno!',
    '¡Me salió el diablo!',
    'Partida finalizada con derrota.',
    'Resultado final: derrota.',
    'Equipo perdedor: "Este jugador".',
    'Jugador con peor desempeño: "El que está llorando aquí".',
    '¡Mejor suerte la próxima vez!',
    '¡A aprender de los errores!',
    '¡Soy un meme!',
    '¡Para el video de fails!',
    '¡De vuelta a la escuela!',
    '¡Necesito un entrenador!',
    '¡El uninstall está cerca!',
    '¡Toca llorar!',
    '¡Soy el peor!',
    '¡Misión fallida!',
    '¡Nos vemos en la próxima partida!',
  ]

  constructor(
    private http: HttpClient
  ) { }

  getWord() {
    return this.http.get<string[]>(this.URL);
  }
}
