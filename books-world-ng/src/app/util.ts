export interface GoogleBooksApis {
  kind: string;
  totalItems: number;
  items: Libro[];
}

export interface Libro {
  volumeInfo: {
    title: string;
    authors: string[];
    publisher: string;
    publishedDate: string;
    description: string;
    pageCount: number;
    categories: string[];
    imageLinks: {
      thumbnail: string;
      smallThumbnail: string;
    };
    language: string;
    industryIdentifiers: Array<{
      type: string;
      identifier: string;
    }> 
  };

  saleInfo: {
    saleability: string;
    listPrice: {
      amount: number,
      currencyCode: string
    }
  }

  accessInfo: {
    epub: {
      isAvailable: boolean;
    };
    webReaderLink?: string;
  }
  prezzo: number;
}


//Interfaccia per API NewYork Times

export interface NewYorkTimesApi{
  status: string,
  num_results: number,
  results: {
    books: NyBook[]
  }
}

export interface NyBook {
  primary_isbn10: string,
  primary_isbn13: string,

  publisher: string,
  description: string,

  title: string,
  author: string,
  book_image: string
}
export class Utente{
  username:string | undefined;
	nome:string | undefined;
	cognome:string | undefined;
	password:string | undefined;
	data_di_nascita:Date | undefined;
	moderatore:boolean | undefined;
	email:string | undefined;
}
export class Recensione {

  id!: BigInt ; 
  titolo!: string;
  testo!: string;
  numeroMiPiace!:number;
  numeroNonMiPiace!:number;
  scrittaDa!: Utente;
  isbn!: string;
  numeroStelle!: number;
  segnalabile!:boolean;
}