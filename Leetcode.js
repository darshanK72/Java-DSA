// Define the list of allowed tags
const allowedTags = ["Array","Recursion", "Bit Manipulation", "Math", "Brainteaser", "Simulation", "Game Theory"];

// Select all table rows
const rows = document.querySelectorAll("tr");

// Log the number of rows found
console.log(`Total rows found: ${rows.length}`);

rows.forEach((row, index) => {
    // Select the td element that contains the tags
    const tagsTd = row.querySelector("td[label='Tags']");

    if (tagsTd) {
        // Select the div inside the td
        const tagsDiv = tagsTd.querySelector("div.tags-cell__I1pn");

        if (tagsDiv) {
            // Get the list of anchor elements within the div
            const anchorTags = tagsDiv.querySelectorAll("a");

            // Get the text content of the tags
            const tagTexts = Array.from(anchorTags).map(a => a.textContent.trim());

            // Check if all tags are within the allowed list
            const allTagsAllowed = tagTexts.every(tag => allowedTags.includes(tag));

            // Log the row index and the result of the check
            console.log(`Row ${index}: All tags allowed = ${allTagsAllowed}`);

            // Hide the row if any tag is not allowed
            if (!allTagsAllowed) {
                row.style.display = "none";
                console.log(`Row ${index} is hidden because it contains disallowed tags`);
            }
        } else {
            console.log(`Row ${index}: Tags div not found`);
        }
    } else {
        console.log(`Row ${index}: Tags column not found`);
    }
});
