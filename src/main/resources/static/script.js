const API = 'http://localhost:8080/api';

   
    // ------- NOTES -------
    async function loadNotes() {
      const code = document.getElementById('subFilter').value.trim();
      const url = code ? `${API}/notes?subject_code=${encodeURIComponent(code)}` : `${API}/notes`;
      const res = await fetch(url);
      const data = await res.json();
      const tbody = document.querySelector('#notesTable tbody');
      tbody.innerHTML = '';
      data.forEach(n => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
          <td>${n.id}</td>
          <td>${n.subject_code} - ${n.subject_name || ''}</td>
          <td>${n.uploader ? n.uploader.name : ''}</td>
          <td>${n.uploaded_at || ''}</td>
          <td class="file-link"><a href="/files/${n.file_path}" target="_blank">${n.file_path}</a></td>
          <td><button onclick='deleteNote(${n.id})'>Delete</button></td>
        `;
        tbody.appendChild(tr);
      });
    }

    async function deleteNote(id) {
      if (!confirm('Delete this note?')) return;
      await fetch(`${API}/notes/${id}`, { method: 'DELETE' });
      loadNotes();
    }

    document.getElementById('noteForm').addEventListener('submit', async e => {
      e.preventDefault();
      const formData = new FormData();
      formData.append('subject_code', document.getElementById('subject_code').value);
      formData.append('subject_name', document.getElementById('subject_name').value);
      formData.append('uploader_staff_id', document.getElementById('uploader_staff_id').value);
      formData.append('description', document.getElementById('description').value);
      formData.append('file', document.getElementById('file').files[0]);

      await fetch(`${API}/notes`, {
        method: 'POST',
        body: formData
      });
      e.target.reset();
      loadNotes();
    });

    // Initial load
    loadStaff();
    loadNotes();