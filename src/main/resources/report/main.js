"use strict";

function init() {
    document.getElementById('title').textContent = main.title;
    document.getElementById('typeCount').textContent = main.typeCount;
    document.getElementById('dependencyCount').textContent = main.dependencyCount;
    document.getElementById('projectCount').textContent = main.projectCount;

    let div = document.getElementById('projects');

    let el = document.createElement('ol');
    div.appendChild(el);
    for (const p of proj) {
        let projEl = document.createElement('li');
        let linkEl = document.createElement('a');
        linkEl.setAttribute("href", "project.html#" + p.id)
        linkEl.textContent = p.name;
        projEl.appendChild(linkEl);
        projEl.appendChild(document.createTextNode(" (" + p.dependencies.length + ")"));
        el.appendChild(projEl);
    }
}