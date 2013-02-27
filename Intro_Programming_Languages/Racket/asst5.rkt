; Name: Dave Hubbard
; Email: dehubbar@asu.edu
; CSE 240 Assignment 5 - English to Pirate English Translation

(require racket/string) ; imports functions like string-join


; Built-in Racket functions you may find useful:
; string-ci=?  which is case insensitive string equality
; char-upcase
; string-append
; string-join
; char-alphabetic?
; char-numeric?
; http://docs.racket-lang.org/guide/characters.html
; http://docs.racket-lang.org/reference/strings.html
; There is also a link to the Racket Guide on the References
; section of the course website.

; The index of the Racket programming guide is here:
; http://docs.racket-lang.org/guide/index.html
; You may find it a handy reference for finding functions you
; want to use or other reference tasks.

; split-by-spaces :: string -> string list
; Given a string, split-by-spaces evaluates to a list of strings, containing the
; words in the original string.  This is a good function for splitting up a sentence
; into words.
(define (split-by-spaces str)
  (regexp-split #px"\\b" str))

; example-piratize-word :: string -> string
; Just an example of how to convert a word to "pirate speak".  Your pirate-word
; function will be a lot cooler than this.
(define (example-pirate-word word) 
  (correct-case word 
                (cond
                  [(string-ci=? word "Hello") "Ahoy"]
                  [(string-ci=? word "Yes") "Aye"]
                  [(string-ci=? word "to") "t'"]
                  [else word])))

; correct-case :: (string, string) -> string
; Corrects the case of the second word, given the first word as an example.
; For example, (correctCase "hello" "Hello") --> "hello"
;              (correctCase "Yo" "sir")      --> "Sir"
; You don't have to use this, but you might find it useful for having correct
; capitalization in your translations.
(define (correct-case originalWord newerWord)
  (list->string
   (let ([origWord (string->list originalWord)]
         [newWord (string->list newerWord)])
     (cond
       [(empty? origWord) newWord] ; just in case
       [(empty? newWord) newWord]  ; just in case
       [(char-upper-case? (car origWord))
        (cons (char-upcase (car newWord)) (cdr newWord))]
       [else
        (cons (char-downcase (car newWord)) (cdr newWord))]
       ))))

(define manwords (list "bastard" "landlubber" "matey" "scallywag" "scurvy dog"
                       "buccaneer" "scum o' the sea"))

(define suffix (list ", Arrrr!" ", Savvy?" ", me hearty!")) 

 (define (pirate-word word)
   (correct-case word  
      (cond
         [(string-ci=? word "Hello") "Ahoy"]
         [(string-ci=? word "Hi") "Ahoy"]
         [(string-ci=? word "Greetings") "Ahoy"]
         [(string-ci=? word "Yo") "Ahoy"]
         [(string-ci=? word "man")(list-ref manwords(random (length
                                                       manwords)))]
         [(string-ci=? word "You") "Ye"]
         [(string-ci=? word "are") "be"]
         [(string-ci=? word "dead") "on the way t' Davy Jones Locker"]
         [(string-ci=? word "to") "t'"]
         [(string-ci=? word "Yes") "Aye"]
         [(string-ci=? word ".") (list-ref suffix(random (length
                                                       suffix)))]
         [(string-ci=? word "!") (list-ref suffix(random (length
                                                       suffix)))]
         [else word])))
 
 (define (piratize phrase)
   (string-join (map pirate-word(split-by-spaces phrase))""))