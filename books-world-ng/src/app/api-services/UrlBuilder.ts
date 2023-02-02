export class UrlBuilder{
    private link: string = "https://www.googleapis.com/books/v1/volumes?q=";

    private searchFromText ?: string;
    private searchFromAuthor ?: string;
    private searchFromPublisher ?: string;
    private searchFromCategory ?: string;
    private searchFromIsbn ?: string;

    private filter: string = "&filter=";
    private printType: string = "&printType=";
    private start_index: string = "&startIndex=";
    private max_results: string = "&maxResults=";
    private order_by: string = "&orderBy=";


    //Parametri aggiuntivi della ricerca con q
    text( content: string ): void{ if( content !== "") this.searchFromText = content; }
    author( content: string ): void{ if( content !== "") this.searchFromAuthor = content; }
    publisher( content: string ): void{ if( content !== "") this.searchFromPublisher = content; }
    category( content: string ): void{ if( content !== "") this.searchFromCategory = content; }
    isbn( content: string): void{ if( content !== "") this.searchFromIsbn = content; }

    //Filtri
    partial(): void { this.filter += "partial"; }
    full(): void { this.filter += "full"; }
    freeBooks(): void { this.filter += "free-ebooks"; }
    paidBooks(): void { this.filter += "paid-ebooks"; }
    ebooks(): void { this.filter += "ebooks"; }

    //Tipo di stampa
    all(): void { this.printType += "all"; }
    books(): void { this.printType += "books"; }
    magazines(): void { this.printType += "magazines"; }

    //Start index e max results
    startIndex( value: number ): void {
        if( value < 0 ) value = 0;
        this.start_index += value as unknown as string;
    }
    maxResults( value: number ): void {
        if( value <= 0 ) value = 10;
        if( value > 40 ) value = 40;
        this.max_results += value as unknown as string;
    }

    //OrderBy
    relevance(): void { this.order_by += "relevance"; }
    newest(): void { this.order_by += "newest"; }

    private stringConstruct(argument: string | null, content: string): string{
        return argument + content.replace(" ", "+");
    }


    build(): string{
        //Costruzione con parametri aggiuntivi query

        if( this.searchFromText !== undefined ){
            //this.searchFromText = this.searchFromText.replace(" ", "+");
            this.link += this.searchFromText;
        } else this.link += "1";

        if( this.searchFromAuthor !== undefined ){
            //this.searchFromAuthor = "onauthor:" + this.searchFromAuthor.replace(" ", "+");
            if( this.searchFromText !== '' ) {
              if (this.link.endsWith("q=")) {
                this.link += "inauthor:" + this.searchFromText;
              }
              else this.link += "+inauthor:" + this.searchFromAuthor;
            }
            else this.link += this.searchFromAuthor;
        }

        if( this.searchFromPublisher !== undefined ){
            //this.searchFromPublisher = "onpublisher:" + this.searchFromPublisher.replace(" ", "+");
            if( this.searchFromPublisher !== '' ) {
              if (this.link.endsWith("q=")) {
                this.link += "inpublisher:" + this.searchFromPublisher;
              }
              else this.link += "+inpublisher:" + this.searchFromPublisher;
            }
            else this.link += this.searchFromPublisher;
        }

        if ( this.searchFromCategory !== undefined ){
          if ( this.searchFromCategory !== '' ) {
            if (this.link.endsWith("q=")) {
              this.link += "subject:" + this.searchFromCategory;
            }
            else this.link += "+subject:" + this.searchFromCategory;
          }
          else this.link += this.searchFromCategory;
        }

        if ( this.searchFromIsbn !== undefined ){
          if ( this.searchFromIsbn !== '' ) {
            if (this.link.endsWith("q=")) {
              this.link += "isbn:" + this.searchFromIsbn;
              console.log(this.link);
            }
            else this.link += "+isbn:" + this.searchFromIsbn;
          }
          else this.link += this.searchFromIsbn;
        }

        

        //Costruzione con filtri
        if( this.filter !== "&filter="  ) this.link += this.filter;

        //Costruzione
        if( this.printType !== "&printType=" ) this.link += this.printType;

        //startIndex e maxResults
        if( this.start_index !== "&startIndex=" ) this.link += this.start_index;
        if( this.max_results !== "&maxResults=" ) this.link += this.max_results;

        //orderBy
        if( this.order_by !== "&orderBy=" ) this.link += this.order_by;


        return this.link;
    }
}


