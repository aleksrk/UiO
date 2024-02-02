from bs4 import BeautifulSoup
import re

import dateutil
import requests

if __name__ == '__main__':
    """
    with open("list.html") as f:
        html = f.read()
        document = BeautifulSoup(html, "html.parser")

    #print(html)
    #find tags by name and return to ulist
    ulist = document.find("ul")
    print(ulist)
    #find all tags that match tag name, returns to a list
    items = ulist.find_all("li")
    print(items)

    #loop over and get contents with get_text

    for item in items:
        print(repr(item.get_text()))
    #whitespace is not meaningful in html, so we strip the whitespace
    for item in items:
        print(repr(item.get_text(strip=True)))

    #find tag by attribute
    by_attr = document.find(attrs={"id": "unordered_list"})
    print(by_attr)

    #the ul tag contains a style attribute, bs4 tags behaves like dictionaries with attr as keys and attr val as val
    print(ulist.attrs)
    print(ulist["style"])

    """
    r = requests.get("https://en.wikipedia.org/wiki/List_of_countries_and_dependencies_by_population")
    assert r.status_code == 200

    #extract html and initiate bs4
    html = r.text
    document = BeautifulSoup(html, "html.parser")

    #use find to find table
    table = document.find("table")
    print(str(table)[:1024])
