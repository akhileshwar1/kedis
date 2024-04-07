## KEDIS

A redis clone in Java.

# Interface

* ``SET <key> <value>``
  * Operation: Sets the key-value pair in the data store. Returns OK/ERROR. 
  * key: Identifies the item that you want to set. 
  * value: Value associated with the the item.
  * Example : ``SET akhil kandi`` --> ``OK``

* ``GET <key>``
  * Operation: Gets the value of the <key> from the data store.
  * key: Identifier of the item in the store. 
  * Example: ``GET akhil`` --> ``kandi``

* ``LADD <key> <value1> <value2> ...``
  * Operation: adds the  the values in a list form as the value of the key. 
  * Example: ``LADD football:players bicho chiquito`` --> ``OK``


* ``LSET <key> <index> <value>``
  * Operation: Sets the value at the index-th posn of the value residing there. 
  * index: number that identifies the index of the value at the key.
  * value: data to store at the index.
  * Example: ``LSET football:players 0 siete``  --> ``OK``

* ``LGET <key> <index>``
  * Operation: Gets the value at the <index>th posn of the list value residing there. 
  * index: number that identifies the index of the value at the key.
  * Example: ``LGET football:players 1`` --> ``chiquito``
