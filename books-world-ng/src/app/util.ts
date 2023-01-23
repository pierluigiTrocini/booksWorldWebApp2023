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
    publishDate: string;
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
