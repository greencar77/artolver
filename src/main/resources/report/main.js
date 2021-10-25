"use strict";

function init() {
    let div = document.getElementById('projects');

    let el = document.createElement('ol');
    div.appendChild(el);
    for (const p of proj) {
        let projEl = document.createElement('li');
        let linkEl = document.createElement('a');
        linkEl.setAttribute("href", "project.html#" + p.id)
        linkEl.textContent = p.name;
        projEl.appendChild(linkEl);
        el.appendChild(projEl);
    }
}