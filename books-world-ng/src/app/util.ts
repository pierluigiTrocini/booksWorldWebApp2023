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
