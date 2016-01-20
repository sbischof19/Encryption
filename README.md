# Encryption
This is a personal research project on implementing various encryption algorithms.
So I know you wanted to be able to research and do a good amount of this on your own in order to prep for wpi a little bit. 
I will be making a github project for this and will let you know when that is up and going, I do have a parent class that 
I am using so I think github is the best way for us to stay in sync. I would like for us to keep that parent class updated 
with general methods pertaining to the encryption ciphers as much as possible. (ex. hex to binary, ascii to hex, etc.) 
The other main challenge on your end I think will be researching and documenting your code to the extent that I can explain 
in depth how each thing works when presenting, I'll of course give you credit if you want in the presentation, and the code 
for the cipher is obviously yours to do with whatever you want (I'm assuming this will be open source on github at some point anyway).

So that you can start your research the basic requirements I'm looking for are:

-Needs to extent CipherMethods parent.
        a. This just makes it easier when it is implemented into the app (less redundancy)
        b. You can fork it and add/modify stuff if needed, then text me so I can see if it works with my AES as well and I will add it.

-Must be as close to industry standard as possible
        a. this means for your RSA you would need to implement OAEP (Optimal Asymmetric Encryption Padding)
        b. If you feel like you don't have time and think the AES would be easier you can do that and I'll do the RSA, just let me know soon.
		c. AES would be in 128 bit any than that more is optional.

-Must return a hex string and be able to encrypt messages of various lengths.
        a. This will be how the 128 bit symmetric keys will be transported
        b. I realize 128 bit AES has specifically 10 keys but various lengths would make it easier to implement if someone wanted to use it for other things.
        c. The AES will also return a hex string as I have found converting randomly encrypted hex to ascii often results in blank squares because the value is higher than the standard ascii table.
		
-Must of course except a hex string for decryption process for both ciphers.

-Must not use a java library.
		a. It's mainly for research at the moment


Let me know if you need to know more, I'll be making some modifications to the CipherMethods class and making the git repository this week.
I'm hoping by the time these are done they will be as secure as possible and if we wanted to make the app public on the google store we would
feel safe using our own algorithms (otherwise I'll use a library). That way any company or school looking at the project would be at the very 
least somewhat impressed by the cipher algoritms.
