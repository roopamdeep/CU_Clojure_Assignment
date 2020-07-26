(require '[clojure.string :as str])

(declare select-option)
(declare invalid-option)
(declare customers-data)
(declare products-data)
(declare sales-data)
(declare total-sales-for-customer)
(declare total-count-for-product)
(declare exit-menu)

(defn parse-int [str]
	(let [n (read-string str)]
		(if (number? n) n nil)))

(defn parse-data
	[data]
	(map #(vec (str/split % #"\|"))
			 (vec (str/split data #"\n"))
			 )
	)

(defn sort-by-id [x y]
	(let [c (compare (parse-int (first x)) (parse-int (first y)))]
		(if (not= c 0)
			c
			)
		)
	)

(defn crunch-data
	[]
	(def customers (sort sort-by-id (parse-data (slurp "cust.txt"))))
	(def products (sort sort-by-id (parse-data (slurp "prod.txt"))))
	(def sales (sort sort-by-id (parse-data (slurp "sales.txt"))))
	)

(defn round
	[x precision]
	(-> x (bigdec) (.movePointRight precision) (+ 0.5) (int) (bigdec) (.movePointLeft precision))
	)

(defn get-customer-by-id
	[id]
	(for [x customers]
		(if (= (nth x 0) (str id)) x)
		)
	)

(defn get-product-by-id
	[id]
	(for [x products]
		(if (= (nth x 0) (str id)) x)
		)
	)

(defn get-customer-by-name
	[name]
	(first (filter #(= (nth % 1) (str name)) customers))
	)

(defn get-product-by-name
	[name]
	(first (filter #(= (nth % 1) (str name)) products))
	)

(defn get-total-price-from-sale
	[sale]
	(* (parse-int (last sale)) (parse-int (last (first (filter some? (get-product-by-id (nth sale 2)))))))
	)

(defn print-table-data
	[item]
	(println (first item)(str ": [")(str/join "," (map #(str \" % \") (drop 1 item)))(str "]"))
	)

(defn menu
	[]
	(println (str "\n *** Sales Menu ***"))
	(println (str "_ _ _ _ _ _ _ _ _ _ \n"))
	(println (str "1. Display Customer Table"))
	(println (str "2. Display Product Table"))
	(println (str "3. Display Sales Table"))
	(println (str "4. Total Sales for Customer"))
	(println (str "5. Total Count for Product"))
	(println (str "6. Exit \n"))
	(println (str "Enter an option?"))

	(let [option (read-line)]
		(select-option option))
	)

(defn select-option
	[option]
	(if (= option "")
		(menu)
		(case option
			"1" (customers-data)
			"2" (products-data)
			"3" (sales-data)
			"4" (total-sales-for-customer)
			"5" (total-count-for-product)
			"6" (exit-menu)
			(invalid-option))
		)
	)

(defn invalid-option
	[]
	(println (str "\nInvalid Option. Please try again."))
	(menu)
	)

(defn customers-data
	[]
	(print "\n")
	(dorun (map #(print-table-data %) customers))
	(menu)
	)

(defn products-data
	[]
	(print "\n")
	(dorun (map #(print-table-data %) products))
	(menu)
	)

(defn sales-data
	[]
	(print "\n")
	(doseq [item sales]
		(def temp [(nth (first (filter some? (get-customer-by-id (nth item 1)))) 1) (nth (first (filter some? (get-product-by-id (nth item 2)))) 1) (last item)])
		(println (first item)(str ": [")(str/join "," (map #(str \" % \") temp))(str "]"))
		)
	(menu)
	)

(defn total-sales-for-customer
	[]
	(def sum (atom 0))
	(println (str "\nCustomer name?"))
	(let [customer (read-line)]
		(reset! sum (reduce + (map #(get-total-price-from-sale %) (filter #(= (first (get-customer-by-name customer)) (nth % 1)) sales))))
		(print "\n")
		(println (str/capitalize customer)(str ":")(str "$" (round @sum 2)))
		(menu)
		)
	)

(defn total-count-for-product
	[]
	(def sum (atom 0))
	(println (str "\nProduct name?"))
	(let [product (read-line)]
		(reset! sum (reduce + (map #(parse-int (last %)) (filter #(= (first (get-product-by-name product)) (nth % 2)) sales))))
		(print "\n")
		(println (str/capitalize product)(str ":") @sum)
		(menu)
		)
	)

(defn exit-menu
	[]
	(println (str "\nGood Bye\n"))
	(System/exit 1)
	)

(crunch-data)
(menu)
