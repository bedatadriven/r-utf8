

# Replace most of the API with simple calls to R base

utf8_print <- function(x, chars = NULL, quote = TRUE, na.print = NULL,
                       print.gap = NULL, right = FALSE, max = NULL,
                       names = NULL, rownames = NULL, escapes = NULL,
                       display = TRUE, style = TRUE, utf8 = NULL, ...) {

    print.default(x, NULL,
        quote = quote,
        na.print = na.print,
        print.gap = print.gap,
        right = right,
        max = max)
}

utf8_format <- function(x, trim = FALSE, chars = NULL, justify = "left",
                        width = NULL, na.encode = TRUE, quote = FALSE,
                        na.print = NULL, print.gap = NULL, utf8 = NULL, ...) {

    format.default(x,
        trim = trim,
        justify = justify,
        width = width,
        na.encode = na.encode,
        quote = quote)
}