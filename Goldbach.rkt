#lang racket

(define (plus2 n) (+ n 2))

(define (repeat n x) 
  (if(= n 0)
     '()
     (cons x (repeat (- n 1) x))
   )
)

(define (prime? x)
  (define list1 (build-list (- x 2) plus2))
  (define len (- x 2))
  (define list2 (repeat len x))
  (define list3 (map (lambda(x1 x2) (modulo x2 x1)) list1 list2))
  (if (member 0 list3)
     #f
     #t
  )
  )

(define (input)
  (displayln "Enter a number that is even and greater than 2")
  (define x(read))    
  (if (even? x)
     (if(> x 2)
        x
        (begin (displayln "That number is not greater than two")(input))
        )
     (begin (displayln "That number is not even")(input))
  
  )
)  

(define (2to x)
  (if (> x 2)
      (cons x ( 2to (- x 1)))
      '(2)
  )
)  

(define (findsum list1 x)
  (define num1 (car list1))
  (define num2 (- x num1))
  (if (member num2 list1)
      (cons num1 num2)
      (findsum (cdr list1) x)
  
  
  )
)


(define (goldbach)
  (define num (input))
  (define primelist (filter prime? (2to num)))
  (define sum (findsum primelist num))
  (printf "The two primes that add to ~a are ~a and ~a" num (car sum) (cdr sum))
  
  )

